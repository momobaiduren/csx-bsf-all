package com.yh.csx.bsf.file.config;

import com.yh.csx.bsf.core.base.BsfEnvironmentEnum;
import com.yh.csx.bsf.core.util.ReflectionUtils;
import lombok.Data;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Huang Zhaoping
 */
@Data
@ConfigurationProperties(prefix = "bsf.file")
public class FileProperties {
    public static final String PROVIDER_HOTFILE = "hotfile";
    public static final String PROVIDER_QINIU = "qiniu";

    public static String Project="File";
    public static String Prefix="bsf.file.";

    private String provider;
    @Value("{$bsf.env:}")
    private String bsfEnv;

    @NestedConfigurationProperty
    private HotFileProperties hotfile;

    @NestedConfigurationProperty
    private QiniuProperties qiniu;

    public HotFileProperties getDefaultHotFile() {
        HotFileProperties properties = new HotFileProperties();
        properties.setUploadUrl(ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","hotfile_url",""));
        properties.setLoginToken(ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","hotfile_logintoken",""));
        return properties;
    }

    public QiniuProperties getDefaultQiniu() {
        QiniuProperties properties = new QiniuProperties();
        properties.setAccessKey(ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","accesskey",""));
        properties.setSecurityKey(ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","securitykey",""));
        val env = BsfEnvironmentEnum.get(BsfEnvironmentEnum.FILE_DEV.getServerkey(),BsfEnvironmentEnum.FILE_DEV);
        properties.setBucketName(env.getEnv().name().equalsIgnoreCase("dev")?"test":"prod");
        properties.setBucketUrl(env.getUrl());
        return properties;
    }
}
