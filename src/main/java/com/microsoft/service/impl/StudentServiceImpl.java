package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.mapper.StudentMapper;
import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;
import com.microsoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> list = studentMapper.page(studentQueryParam);
        Page<Student> p = (Page<Student>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
