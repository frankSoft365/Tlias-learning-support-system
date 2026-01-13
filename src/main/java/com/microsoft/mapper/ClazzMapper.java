package com.microsoft.mapper;

import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 获取班级列表
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级
     * @param clazz
     */
    void insert(Clazz clazz);
}
