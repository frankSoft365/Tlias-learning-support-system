package com.microsoft.controller;

import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Result;
import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;
import com.microsoft.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    @GetMapping("/students")
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学员列表查询");
        PageResult<Student> list = studentService.page(studentQueryParam);
        return Result.success(list);
    }
}
