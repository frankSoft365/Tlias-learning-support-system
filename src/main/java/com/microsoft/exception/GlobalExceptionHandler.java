package com.microsoft.exception;

import com.microsoft.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错了", e);
        return Result.error("服务器出错！");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("重复错误", e);
        // 获取错误信息
        String message = e.getMessage();
        // 获取重复字段所在的字符串首部
        int i = message.indexOf("Duplicate entry");
        // 将重复字段放进数组
        String substring = message.substring(i);
        String[] arr = substring.split(" ");
        // 拼接重复字段与提示信息
        return Result.error( arr[2] + "已存在");
    }

}
