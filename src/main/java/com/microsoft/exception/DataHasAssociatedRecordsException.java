package com.microsoft.exception;

// 数据存在关联记录，无法执行删除
public class DataHasAssociatedRecordsException extends RuntimeException {
    public DataHasAssociatedRecordsException(String message) {
        super(message);
    }
}
