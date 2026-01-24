package com.microsoft.controller;

import com.microsoft.anno.LogOperation;
import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Result;
import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;
import com.microsoft.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 学员列表查询
     */
    @GetMapping("/students")
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学员列表查询");
        PageResult<Student> list = studentService.page(studentQueryParam);
        return Result.success(list);
    }

    /**
     * 新增学员
     */
    @LogOperation
    @PostMapping("/students")
    public Result add(@RequestBody Student student) {
        log.info("新增学员");
        studentService.add(student);
        return Result.success();
    }

    /**
     * 根据id查询学员信息
     */
    @GetMapping("/students/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        log.info("查询回显");
        Student stu = studentService.getInfoById(id);
        return Result.success(stu);
    }

    /**
     * 修改学员
     */
    @LogOperation
    @PutMapping("/students")
    public Result update(@RequestBody Student student) {
        log.info("修改学员");
        studentService.update(student);
        return Result.success();
    }

    /**
     * 根据id删除学员
     */
    @LogOperation
    @DeleteMapping("/students/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除学员：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @LogOperation
    @PutMapping("/students/violation/{id}/{score}")
    public Result violationAction(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理，违纪学员id：{}，违纪扣分：{}", id, score);
        studentService.violationAction(id, score);
        return Result.success();
    }
}
