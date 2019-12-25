package com.yh.csx.bsf.file.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import com.yh.csx.bsf.core.serialize.JsonSerializer;
import com.yh.csx.bsf.file.FileException;
import com.yh.csx.bsf.file.config.QiniuProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Huang Zhaoping
 */
public class QiniuFileProvider extends AbstractFileProvider {

    private Auth auth;
    private String bucketName;
    private String bucketUrl;
    private UploadManager uploadManager;
    private BucketManager bucketManager;

    public QiniuFileProvider(QiniuProperties properties) {
        if (StringUtils.isNullOrEmpty(properties.getAccessKey())) {
            throw new FileException("七牛文件服务缺少accessKey配置");
        }
        if (StringUtils.isNullOrEmpty(properties.getSecurityKey())) {
            throw new FileException("七牛文件服务缺少securityKey配置");
        }
        if (StringUtils.isNullOrEmpty(properties.getBucketName())) {
            throw new FileException("七牛文件服务缺少bucketName配置");
        }
        if (StringUtils.isNullOrEmpty(properties.getBucketUrl())) {
            throw new FileException("七牛文件服务缺少bucketUrl配置");
        }
        this.auth = Auth.create(properties.getAccessKey(), properties.getSecurityKey());
        Configuration configuration = new Configuration(Zone.autoZone());
        String tempDir = properties.getTempDir();
        if (StringUtils.isNullOrEmpty(tempDir)) {
            tempDir = "tmp/";
        }
        try {
            this.uploadManager = new UploadManager(configuration, new FileRecorder(tempDir));
        } catch (IOException e) {
            throw new FileException("创建七牛文件临时目录失败：" + tempDir, e);
        }
        this.bucketManager = new BucketManager(this.auth, configuration);
        this.bucketName = properties.getBucketName();
        String bucketUrl = properties.getBucketUrl();
        if (!bucketUrl.endsWith("/")) {
            bucketUrl += "/";
        }
        this.bucketUrl = bucketUrl;
    }

    @Override
    public String upload(InputStream input, String path, String name) {
        try {
            String token = auth.uploadToken(bucketName);
            Response response = uploadManager.put(input, createFileKey(path, name), token, null, null);
            if (response.statusCode != 200) {
                throw new FileException("七牛文件上传异常：" + response.statusCode);
            }
            DefaultPutRet putRet = new JsonSerializer().deserialize(response.bodyString(), DefaultPutRet.class);
            return bucketUrl + putRet.key;
        } catch (QiniuException e) {
            throw new FileException("七牛文件服务异常", e);
        } catch (Exception e) {
            throw new FileException("七牛上传文件异常", e);
        }
    }
    /**
     * 	上传文件
     *
     * @param filePath 上传的本地文件名
     * @param path     上传文件路径
     * @param name     上传文件名
     */
    @Override
    public String upload(String filePath, String path, String name) {
    	 try {
             String token = auth.uploadToken(bucketName);
             Response response = uploadManager.put(filePath, createFileKey(path, name), token);
             if (response.statusCode != 200) {
                 throw new FileException("七牛文件上传异常：" + response.statusCode);
             }
             DefaultPutRet putRet = new JsonSerializer().deserialize(response.bodyString(), DefaultPutRet.class);
             return bucketUrl + putRet.key;
         } catch (QiniuException e) {
             throw new FileException("七牛文件服务异常", e);
         } catch (Exception e) {
             throw new FileException("七牛上传文件异常", e);
         }
    }

    @Override
    public boolean delete(String url) {
        String path;
        try {
            path = new URL(url).getPath();
            if (path.startsWith("/")) path = path.substring(1);
        } catch (MalformedURLException e) {
            throw new FileException("URL错误：" + url, e);
        }
        try {
            return bucketManager.delete(bucketName, path).statusCode == 200;
        } catch (QiniuException e) {
            return false;
        }
    }
  
    public String info() {
        return "qiniu:"+com.yh.csx.bsf.core.util.StringUtils.nullToEmpty(bucketUrl);
    } 

}
