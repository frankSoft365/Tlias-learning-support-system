package com.microsoft.mapper;

import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
     */
    void insert(Student student);
}
