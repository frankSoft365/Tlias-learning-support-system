package com.microsoft.controller;

import com.aliyuncs.exceptions.ClientException;
import com.microsoft.pojo.Result;
import com.microsoft.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException, ClientException {
        log.info("传过来的文件名为：" + file.getOriginalFilename());
        // 上传到阿里云oss
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("图片的url：" + url);
        return Result.success(url);
    }
}
