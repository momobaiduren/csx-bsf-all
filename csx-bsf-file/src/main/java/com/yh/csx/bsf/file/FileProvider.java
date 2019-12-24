package com.yh.csx.bsf.file;

import java.io.InputStream;
/**
 * @author Huang Zhaoping
 */
public interface FileProvider {

    /**
     * 上传文件
     *
     * @param input 文件流
     * @param name  文件名
     * @return
     */
    String upload(InputStream input, String name);

    /**
     * 	上传文件
     *
     * @param filePath 上传的本地文件名
     * @param path     上传文件路径
     * @param name     上传文件名
     */
    String upload(String filePath, String name);
    /**
     * 上传临时文件
     *
     * @param input 文件流
     * @param name  文件名
     * @return
     */
    String uploadTemp(InputStream input, String name);
    /**
     * 	上传文件
     *
     * @param filePath 上传的本地文件名
     * @param path     上传文件路径
     * @param name     上传文件名
     */
    String uploadTemp(String filePath, String name);

    /**
     * 删除文件
     *
     * @param url
     * @return
     */
    boolean delete(String url);

}
