package com.microsoft.pojo;

import lombok.Data;

@Data
public class Result {
    // 状态码 1 成功 0 失败
    private Integer code;
    // 错误信息
    private String msg;
    // 数据
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }
    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
