package com.microsoft.controller;

import com.microsoft.anno.LogOperation;
import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpQueryParam;
import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Result;
import com.microsoft.service.EmpService;
import com.microsoft.service.impl.EmpServiceImpl;
import com.microsoft.utils.CurrentHold;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 前端传过来分页查询、条件查询的参数并将其封装成为empQueryParam对象
     */
    @GetMapping("/emps")
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询，参数：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据前端传来的json员工信息封装添加到数据库
     */
    @LogOperation
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工的信息：{}", emp);
        empService.add(emp);
        return Result.success();
    }

    /**
     * 根据id删除员工
     */
    @LogOperation
    @DeleteMapping("/emps")
    // 当使用list集合接收参数时要添加注解@RequestParam
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("要删除的员工id : {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询回显员工的基本信息和工作经历
     */
    @GetMapping("/emps/{id}")
    public Result getEmpInfo(@PathVariable Integer id) {
        log.info("要查询回显的员工id : {}", id);
        Emp emp = empService.getEmpInfo(id);
        return Result.success(emp);
    }

    /**
     * 用新的信息更新员工信息
     */
    @LogOperation
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("更改员工信息 : {}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工
     */
    @GetMapping("/emps/list")
    public Result list() {
        log.info("查询所有员工");
        List<Emp> list = empService.list();
        return Result.success(list);
    }

    /**
     * 根据有效的token获取员工信息
     */
    @GetMapping("/emps/profile")
    public Result getUserProfile() {
        log.info("根据登录后的token获取用户个人信息");
        // 能访问此接口说明token有效 这个线程的ThreadLocal中存放有这个token的员工的id信息
        Integer empId = CurrentHold.getCurrentId();
        // 根据员工id查询员工信息并返回
        Emp empInfo = empService.getEmpInfo(empId);
        return Result.success(empInfo);
    }
}
