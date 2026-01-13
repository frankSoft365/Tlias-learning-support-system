package com.microsoft.service;

import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import com.microsoft.pojo.PageResult;

public interface ClazzService {
    /**
     * 获取班级列表
     * @param clazzQueryParam
     * @return
     */
    PageResult<Clazz> getList(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级
     * @param clazz
     */
    void add(Clazz clazz);
}
