package com.yh.csx.bsf.core.http;

import com.yh.csx.bsf.core.config.CoreProperties;
import com.yh.csx.bsf.core.util.EnumUtils;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.core.util.PropertyUtils;
import lombok.Data;
import lombok.val;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author: chejiangyi
 * @version: 2019-07-23 17:54
 **/
@ConfigurationProperties
public class HttpClientProperties {
    public static String Prefix="bsf.httpclient.";
    /**
    * Tcp是否粘包(批量封包发送)
     */
    public static boolean tcpNoDelay() {return PropertyUtils.getPropertyCache("bsf.httpclient.tcpNoDelay",(boolean)HttpClient.EnumHttpConnectParam.TcpNoDelay.getDefaultValue());}
    /**
     * 总连接池大小
     */
    public static int maxTotal(){return PropertyUtils.getPropertyCache ("bsf.httpclient.maxTotal",(int)HttpClient.EnumHttpConnectParam.MaxTotal.getDefaultValue());}

    /**
     * 单个host连接池大小
     *
     */
    public static int defaultMaxPerRoute(){return PropertyUtils.getPropertyCache ("bsf.httpclient.defaultMaxPerRoute",(int)HttpClient.EnumHttpConnectParam.DefaultMaxPerRoute.getDefaultValue());}

    /**
     * 连接是否需要验证有效时间
     */
    public static int validateAfterInactivity(){return PropertyUtils.getPropertyCache("bsf.httpclient.validateAfterInactivity", (int)HttpClient.EnumHttpConnectParam.ValidateAfterInactivity.getDefaultValue());}
    /**
     * 连接超时时间 【常用】
     */
    public static int connectTimeout(){return PropertyUtils.getPropertyCache ("bsf.httpclient.connectTimeout",(int)HttpClient.EnumHttpConnectParam.ConnectTimeout.getDefaultValue());}
    /**
     * socket通讯超时时间 【常用】
     */
    public static int socketTimeout() { return PropertyUtils.getPropertyCache ("bsf.httpclient.socketTimeout",(int)HttpClient.EnumHttpConnectParam.SocketTimeout.getDefaultValue());}

    /**
     * 请求从连接池获取超时时间
     *
     */
    public static int connectionRequestTimeout() {return PropertyUtils.getPropertyCache ("bsf.httpclient.connectionRequestTimeout",(int)HttpClient.EnumHttpConnectParam.ConnectionRequestTimeout.getDefaultValue());}

    /**
     * 连接池共享
     *
     */
    public static boolean connectionManagerShared() {return PropertyUtils.getPropertyCache ("bsf.httpclient.connectionManagerShared",(boolean)HttpClient.EnumHttpConnectParam.ConnectionManagerShared.getDefaultValue());}
    /**
     * 回收时间间隔 s
     */
    public static int evictIdleConnectionsTime() {return PropertyUtils.getPropertyCache ("bsf.httpclient.evictIdleConnectionsTime",(int)HttpClient.EnumHttpConnectParam.EvictIdleConnectionsTime.getDefaultValue());}
    /**
     * 是否回收
     */
    public static boolean isEvictExpiredConnections() {return PropertyUtils.getPropertyCache("bsf.httpclient.isEvictExpiredConnections",(boolean)HttpClient.EnumHttpConnectParam.IsEvictExpiredConnections.getDefaultValue());}
    /**
     * 长连接保持时间 s
     */
    public static int connectionTimeToLive() {return PropertyUtils.getPropertyCache("bsf.httpclient.connectionTimeToLive",(int)HttpClient.EnumHttpConnectParam.ConnectionTimeToLive.getDefaultValue());}
    /**
     * 重试次数 【常用】
     */
    public static int retryCount() {return PropertyUtils.getPropertyCache("bsf.httpclient.retryCount",(int)HttpClient.EnumHttpConnectParam.RetryCount.getDefaultValue());}

    public static HttpClient.InitMap toMap()
    {
        HttpClient.InitMap initMap = new HttpClient.InitMap();
        for (Method m : HttpClientProperties.class.getMethods()) {
                try {
                    val en = HttpClient.EnumHttpConnectParam.get(m.getName());
                    if(en!=null) {
                        initMap.trySetDefaultParams(en, m.invoke(null));
                    }
                } catch (Exception e) {
                    LogUtils.error(HttpClientProperties.class, CoreProperties.Project, "httpclient toMap", e);
                }
        }
        return initMap;
    }
}
