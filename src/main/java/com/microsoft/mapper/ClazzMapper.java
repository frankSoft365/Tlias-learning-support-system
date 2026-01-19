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

    /**
     * 根据ID查询班级信息
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
     * 根据班级id查询对应班级有多少名学生
     * @param id
     * @return
     */
    Integer countStudentInClazz(Integer id);

    /**
     * 根据id删除班级
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询所有班级
     * @return
     */
    List<Clazz> getAllClazz();

    // 根据班主任id查询班级
    Integer getByMasterId(Integer masterId);
}
