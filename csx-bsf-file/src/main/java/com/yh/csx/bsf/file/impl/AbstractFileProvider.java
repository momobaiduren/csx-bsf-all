package com.yh.csx.bsf.file.impl;

import com.yh.csx.bsf.file.FileProvider;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Huang Zhaoping
 */
public abstract class AbstractFileProvider implements FileProvider {
    private static final String IMAGES = "images/", DOCS = "docs/", EXCELS = "excels/", FILES = "files/", TEMP = "temp/";
    private static Map<String, String> defaultPrefix = new HashMap<>();

    static {
        defaultPrefix.put("jpg", IMAGES);
        defaultPrefix.put("png", IMAGES);
        defaultPrefix.put("jpeg", IMAGES);
        defaultPrefix.put("gif", IMAGES);
        defaultPrefix.put("bmp", IMAGES);
        defaultPrefix.put("doc", DOCS);
        defaultPrefix.put("docx", DOCS);
        defaultPrefix.put("pdf", DOCS);
        defaultPrefix.put("txt", DOCS);
        defaultPrefix.put("xlsx", EXCELS);
        defaultPrefix.put("xls", EXCELS);
        defaultPrefix.put("csv", EXCELS);
        defaultPrefix.put("tmp", TEMP);
        defaultPrefix.put("temp", TEMP);
    }

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    protected String createFileKey(String path, String name) {
        String suffix = null;
        if (name != null && name.lastIndexOf('.') >= 0) {
            suffix = name.substring(name.lastIndexOf('.') + 1).trim().toLowerCase();
        }
        if (path != null) {
            while (path.startsWith("/")) {
                path = path.substring(1);
            }
        }
        if (path == null || (path = path.trim()).length() == 0) {
            path = suffix == null ? FILES : defaultPrefix.getOrDefault(suffix, FILES);
        }
        StringBuilder builder = new StringBuilder(path);
        if (!path.endsWith("/")) {
            builder.append("/");
        }
        builder.append(dateTimeFormatter.format(LocalDateTime.now())).append("/");
        builder.append(randomName());
        if (suffix != null && suffix.length() > 0) {
            builder.append('.').append(suffix);
        }
        return builder.toString();
    }

    private String randomName() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    @Override
    public String upload(InputStream input, String name) {
        return FileProviderMonitor.hook().run("upload", () -> upload(input, null, name));
    }
    @Override
    public String upload(String filePath,String name) {
        return FileProviderMonitor.hook().run("upload", () -> upload(filePath, null, name));
    }

    @Override
    public String uploadTemp(InputStream input, String name) {
        return FileProviderMonitor.hook().run("uploadTemp", () -> upload(input, TEMP, name));
    }
    @Override
    public String uploadTemp(String filePath, String name) {
        return FileProviderMonitor.hook().run("uploadTemp", () -> upload(filePath, TEMP, name));
    }

    /**
     * 	上传文件
     *
     * @param input 文件流
     * @param path  存储路径
     * @param name  文件名
     * @return
     */
    public abstract String upload(InputStream input, String path, String name);
    /**
     * 	上传文件
     *
     * @param input 文件流
     * @param path  存储路径
     * @param name  文件名
     * @return
     */
    public abstract String upload(String filePath, String path, String name);
    
    public abstract String info();


}
