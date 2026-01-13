package com.microsoft.controller;

import com.microsoft.pojo.Result;
import com.microsoft.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 前端访问的文件上传接口，将前端传递的二进制文件上传到阿里云
     * @param file
     * @return 返回该文件在阿里云的对外url给前端
     * @throws Exception
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("传过来的文件名为：" + file.getOriginalFilename());
        // 上传到阿里云oss
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("图片的url：" + url);
        return Result.success(url);
    }
}
