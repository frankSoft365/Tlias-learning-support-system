package com.microsoft.service;

import com.microsoft.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询部门列表
     * @return
     */
    List<Dept> findAll();

    /**
     * 通过部门id删除部门
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过封装的部门对象数据添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 通过新的部门对象更改部门数据
     * @param dept
     */
    void update(Dept dept);

    /**
     * 根据id查询部门的信息
     * @param id
     * @return 部门对象 封装了对应id的部门的属性
     */
    Dept getInfoById(Integer id);
}
