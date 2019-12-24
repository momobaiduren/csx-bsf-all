package com.yh.csx.bsf.file.config;

import com.yh.csx.bsf.core.config.BsfConfiguration;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.file.FileException;
import com.yh.csx.bsf.file.FileProvider;
import com.yh.csx.bsf.file.impl.AbstractFileProvider;
import com.yh.csx.bsf.file.impl.HotFileProvider;
import com.yh.csx.bsf.file.impl.QiniuFileProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;


/**
 * @author Huang Zhaoping
 **/
@Configuration
@Import(BsfConfiguration.class)
@EnableConfigurationProperties(FileProperties.class)
@ConditionalOnProperty(name = "bsf.file.enabled", havingValue = "true")
public class FileConfiguration implements InitializingBean {

    @Bean
    @Lazy
    public FileProvider fileProvider(FileProperties properties) {
        FileProvider fileProvider = null;
        if (!StringUtils.hasText(properties.getProvider())) {
            // 未配置提供者的情况根据实际配置来自动选择
            if (properties.getQiniu() != null) {
                fileProvider= new QiniuFileProvider(properties.getQiniu());
            } else if (properties.getHotfile() != null) {
                fileProvider= new HotFileProvider(properties.getHotfile());
            } else {
                fileProvider= new QiniuFileProvider(properties.getDefaultQiniu());
            }
        } else if (FileProperties.PROVIDER_QINIU.equalsIgnoreCase(properties.getProvider())) {
            QiniuProperties qiniu = properties.getQiniu();
            if (qiniu == null) {
                qiniu = properties.getDefaultQiniu();
            }
            fileProvider= new QiniuFileProvider(qiniu);
        } else if (FileProperties.PROVIDER_HOTFILE.equalsIgnoreCase(properties.getProvider())) {
            HotFileProperties hotFile = properties.getHotfile();
            if (hotFile == null) {
                hotFile = properties.getDefaultHotFile();
            }
            fileProvider= new HotFileProvider(hotFile);
        } else {
            throw new FileException("不支持的文件服务提供者：" + properties.getProvider());
        }
        LogUtils.info(FileConfiguration.class, FileProperties.Project, "已启动,"+((AbstractFileProvider)fileProvider).info());
        return fileProvider;
    }

    @Override
    public void afterPropertiesSet() {
        LogUtils.info(FileConfiguration.class, "file", "已启动");
    }
}
