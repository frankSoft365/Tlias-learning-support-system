package com.microsoft.service;

import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import com.microsoft.pojo.PageResult;

import java.util.List;

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

    /**
     *  根据ID查询班级信息
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 更改班级信息
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * 根据id删除班级
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询所有班级
     * @return
     */
    List<Clazz> list();
}
