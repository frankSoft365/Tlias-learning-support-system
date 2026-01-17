package com.microsoft.service;

import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;

public interface StudentService {

    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);
}
