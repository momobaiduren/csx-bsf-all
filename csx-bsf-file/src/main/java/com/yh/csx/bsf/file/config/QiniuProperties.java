package com.yh.csx.bsf.file.config;

import lombok.Data;

/**
 * @author Huang Zhaoping
 */
@Data
public class QiniuProperties {

    private String accessKey;
    private String securityKey;
    private String bucketName;
    private String bucketUrl;
    private String tempDir;

}
