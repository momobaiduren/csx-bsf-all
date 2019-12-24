package com.yh.csx.bsf.cat;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.core.util.StringUtils;

import lombok.val;

/**
 * @author: chejiangyi
 * @version: 2019-08-31 10:18
 **/
@Configuration
@ConditionalOnClass(name = {"org.apache.ibatis.plugin.Interceptor","org.mybatis.spring.transaction.SpringManagedTransaction"})
public class CatMybatisPluginConfiguration {
    @Bean
    public org.apache.ibatis.plugin.Interceptor interceptor() {
        return new CatMybatisPlugin();
    }

    /**
     * 对MyBatis进行拦截，添加Cat监控
     *
     * @author Steven
     */
    @Intercepts({
            @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class,
                    RowBounds.class, ResultHandler.class }),
            @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class,
                    RowBounds.class, ResultHandler.class, CacheKey.class,BoundSql.class}),
            @Signature(method = "update", type = Executor.class, args = { MappedStatement.class, Object.class }) })
    public static class CatMybatisPlugin implements Interceptor {

        // 缓存，提高性能
        private static final Map<String, String> sqlURLCache = new ConcurrentHashMap<String, String>(256);

        private static final String EMPTY_CONNECTION = "jdbc:mysql://unknown:3306/%s?useUnicode=true";

        private Executor target;

        // druid 数据源的类名称
        private static final String DruidDataSourceClassName = "com.alibaba.druid.pool.DruidDataSource:getUrl";
        // dbcp 数据源的类名称
        private static final String DBCPBasicDataSourceClassName = "org.apache.commons.dbcp.BasicDataSource:getUrl";
        // dbcp2 数据源的类名称
        private static final String DBCP2BasicDataSourceClassName = "org.apache.commons.dbcp2.BasicDataSource:getUrl";
        // c3p0 数据源的类名称
        private static final String C3P0ComboPooledDataSourceClassName = "com.mchange.v2.c3p0.ComboPooledDataSource:getJdbcUrl";
        // HikariCP 数据源的类名称
        private static final String HikariCPDataSourceClassName = "com.zaxxer.hikari.HikariDataSource:getJdbcUrl";
        // BoneCP 数据源的类名称
        private static final String BoneCPDataSourceClassName = "com.jolbox.bonecp.BoneCPDataSource:getJdbcUrl";

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            // 得到类名，方法
            String[] strArr = mappedStatement.getId().split("\\.");
            String methodName = strArr[strArr.length - 2] + "." + strArr[strArr.length - 1];

            Transaction t = Cat.newTransaction("SQL", methodName);

            // 得到sql语句
            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            org.apache.ibatis.session.Configuration configuration = mappedStatement.getConfiguration();
            String sql = showSql(configuration, boundSql);

            // 获取SQL类型
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            Cat.logEvent("SQL.Method", sqlCommandType.name().toLowerCase(), Message.SUCCESS, sql);

            String s = this.getSQLDatabase();
            Cat.logEvent("SQL.Database", s);

            Object returnObj = null;
            try {
                returnObj = invocation.proceed();
                t.setStatus(Transaction.SUCCESS);
            } catch (Exception e) {
                Cat.logError(e);
                throw e;
            } finally {
                t.complete();
            }

            return returnObj;
        }

        private javax.sql.DataSource getDataSource() {
            org.apache.ibatis.transaction.Transaction transaction = this.target.getTransaction();
            if (transaction == null) {
                LogUtils.error(CatMybatisPlugin.class, CatProperties.Project, String.format("Could not find transaction on target [%s]", this.target), null);
                return null;
            }
            if (transaction instanceof SpringManagedTransaction) {
                String fieldName = "dataSource";
                Field field = ReflectionUtils.findField(transaction.getClass(), fieldName, javax.sql.DataSource.class);

                if (field == null) {
                    LogUtils.error(CatMybatisPlugin.class, CatProperties.Project, String.format("Could not find field [%s] of type [%s] on target [%s]", fieldName,
                            javax.sql.DataSource.class, this.target), null);
                    return null;
                }

                ReflectionUtils.makeAccessible(field);
                javax.sql.DataSource dataSource = (javax.sql.DataSource) ReflectionUtils.getField(field, transaction);
                return dataSource;
            }

            LogUtils.error(CatMybatisPlugin.class, CatProperties.Project, String.format("---the transaction is not SpringManagedTransaction:%s",
                    transaction.getClass().toString()), null);

            return null;
        }

        /**
         * 重写 getSqlURL 方法
         *
         * @author fanlychie (https://github.com/fanlychie)
         */
        private String getSqlURL() {
            // 客户端使用的数据源
            javax.sql.DataSource dataSource = this.getDataSource();
            if (dataSource != null) {
                String[] classs = new String[]{DruidDataSourceClassName,DBCPBasicDataSourceClassName,DBCP2BasicDataSourceClassName,
                        C3P0ComboPooledDataSourceClassName,HikariCPDataSourceClassName,BoneCPDataSourceClassName};
                for (String cls:classs){
                    val clsName = cls.split(":")[0];
                    val clsMethod = cls.split(":")[1];
                    Class<?> dataSourceClass = com.yh.csx.bsf.core.util.ReflectionUtils.tryClassForName(clsName);
                    if(dataSourceClass!=null && dataSourceClass.isAssignableFrom(dataSource.getClass()))
                    {return com.yh.csx.bsf.core.util.ReflectionUtils.tryCallMethod(dataSource,clsMethod,null,"");}
                }

            }
            return null;
        }

        private String getSQLDatabase() {
//        String dbName = RouteDataSourceContext.getRouteKey();
            String dbName = null; // 根据设置的多数据源修改此处,获取dbname
            if (dbName == null) {
                dbName = "DEFAULT";
            }
            String url = CatMybatisPlugin.sqlURLCache.get(dbName);
            if (url != null) {
                return url;
            }

            url = this.getSqlURL();// 目前监控只支持mysql ,其余数据库需要各自修改监控服务端
            if (url == null) {
                url = String.format(EMPTY_CONNECTION, dbName);
            }
            CatMybatisPlugin.sqlURLCache.put(dbName, url);
            return url;
        }

        /**
         * 解析sql语句
         *
         * @param configuration 配置
         * @param boundSql      sql
         * @return sql
         */
        public String showSql(org.apache.ibatis.session.Configuration configuration, BoundSql boundSql) {
            Object parameterObject = boundSql.getParameterObject();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
            if (parameterMappings.size() > 0 && parameterObject != null) {
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                   //替换？参数
			int j = 0;
			int i = 0;
			StringBuilder newSql = new StringBuilder();				
			
			for (ParameterMapping parameterMapping : parameterMappings) {
				String propertyName = parameterMapping.getProperty();
				if (metaObject.hasGetter(propertyName)) {
					j = sql.indexOf("?", i);
					newSql.append(sql.substring(i, j));
					i = j + 1;													
					newSql.append(Matcher.quoteReplacement(getParameterValue(metaObject.getValue(propertyName))));
				
				} else if (boundSql.hasAdditionalParameter(propertyName)) {
					j = sql.indexOf("?", i);
					newSql.append(sql.substring(i, j));
					i = j + 1;										
					newSql.append(Matcher.quoteReplacement(getParameterValue( boundSql.getAdditionalParameter(propertyName))));
				}
			}
			if (i < sql.length()) {
				newSql.append(sql.substring(i));
			}
			return newSql.toString();	
                }
            }
            return sql;
        }
        
//		private String enrichSqlParameter(String sql, List<String> parameterStrs) {
//			if (StringUtils.isEmpty(sql) || parameterStrs == null || parameterStrs.size() <= 0) {
//				return sql;
//			}
//
//			List<String> finalList = Arrays.asList(sql.split("\\?"));
//			StringBuilder temp = new StringBuilder();
//			int parameterSize = finalList.size();
//			for (int i = 0; i < parameterSize - 1; i++) {
//				temp.append(finalList.get(i));
//				if (i < parameterStrs.size()) {
//					temp.append(parameterStrs.get(i));
//				} else {
//					temp.append("");
//				}
//			}
//			temp.append(finalList.get(parameterSize - 1));
//			return temp.toString();
//		}

		private String enrichSqlParameter(String sql, List<String> parameterStrs) {
			if (StringUtils.isEmpty(sql) || parameterStrs == null || parameterStrs.size() <= 0) {
				return sql;
			}

			char[] sourceArr = sql.toCharArray();
			int finalLength = sourceArr.length;
			for (String param : parameterStrs) {
				finalLength = finalLength + param.length() - 1;
			}
			char[] destArr = new char[finalLength];
			int destIndex = 0;
			int paramIndex = 0;
			for (int i = 0; i < sourceArr.length; i++) {
				char c = sourceArr[i];
				if (c == '?') {
					String param = parameterStrs.get(paramIndex++);
					char[] paramArr = param.toCharArray();
					for (char d : paramArr) {
						destArr[destIndex++] = d;
					}
				} else {
					destArr[destIndex++] = c;
				}
			}
			return new String(destArr);
		}

        /**
         * 参数解析
         *
         * @param obj
         * @return
         */
        private String getParameterValue(Object obj) {
            String value = null;
            if (obj instanceof String) {
                value = "'" + obj.toString() + "'";
            } else if (obj instanceof Date) {
                DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
                value = "'" + formatter.format(obj) + "'";
            } else {
                if (obj != null) {
                    value = obj.toString();
                } else {
                    value = "";
                }

            }
            return value;
        }

        @Override
        public Object plugin(Object target) {
            if (target instanceof Executor) {
                this.target = (Executor) target;
                return Plugin.wrap(target, this);
            }
            return target;
        }

        @Override
        public void setProperties(Properties properties) {
        }
    }
}
