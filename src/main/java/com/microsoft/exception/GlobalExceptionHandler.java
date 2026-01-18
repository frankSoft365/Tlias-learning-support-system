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

    /**
     * 当班级存在学员时，该班级不能被删除
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleClazzHasStudentsCannotDeleteException(ClazzHasStudentsCannotDeleteException e) {
        log.error("班级已存在学生而不可删除错误", e);
        return Result.error(e.getMessage());
    }

    /**
     * 当违纪次数达到最大值时，不能继续增加违纪次数，违纪扣分处理被阻止
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleMaxValueExceededException(MaxValueExceededException e) {
        log.error("数值达到最大限度无法继续增加异常", e);
        return Result.error(e.getMessage());
    }

}
