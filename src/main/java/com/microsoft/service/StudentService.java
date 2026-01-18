package com.microsoft.service;

import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
     */
    void add(Student student);

    /**
     * 根据id查询学员信息
     * @param id
     * @return
     */
    Student getInfoById(Integer id);

    /**
     * 修改学员
     * @param student
     */
    void update(Student student);

    /**
     * 根据id删除学员
     * @param ids
     */
    void delete(List<Integer> ids);
}
