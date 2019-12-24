package com.yh.csx.bsf.demo.file;

import com.yh.csx.bsf.file.FileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Huang Zhaoping
 */
@RestController
@SpringBootApplication
public class FileApplication {

    @Autowired(required = false)
    private FileProvider fileProvider;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) throws Exception {
        return fileProvider.upload(file.getInputStream(), file.getOriginalFilename());
    }

    @PostMapping("/uploadTemp")
    public String uploadTemp(MultipartFile file) throws Exception {
        return fileProvider.uploadTemp(file.getInputStream(), file.getOriginalFilename());
    }

    @PostMapping("/deleteFile")
    public boolean deleteFile(String url) throws Exception {
        return fileProvider.delete(url);
    }

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
