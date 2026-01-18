package com.microsoft.exception;

// 要修改、删除的数据（或父表数据）已被删除
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
