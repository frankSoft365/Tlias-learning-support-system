package com.microsoft.mapper;

import com.microsoft.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门查询
 */
@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    /**
     *根据ID查询部门信息
     */
    @Select("select id, name, dept.create_time, dept.update_time from dept where id = #{id}")
    Dept getInfoById(Integer id);
}
