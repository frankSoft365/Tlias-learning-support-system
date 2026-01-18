package com.microsoft;

import com.microsoft.mapper.DeptMapper;
import com.microsoft.mapper.StudentMapper;
import com.microsoft.pojo.Dept;
import com.microsoft.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TliasLearningSupportSystemApplicationTests {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @Test
    public void testViolationAction() {
        studentService.violationAction(2, 3);
    }

}
