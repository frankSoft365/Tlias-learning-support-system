package com.microsoft.controller;

import com.microsoft.pojo.Dept;
import com.microsoft.pojo.Result;
import com.microsoft.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeptController {


    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     * @return Result对象
     */
    @GetMapping("/depts")
    public Result list() {
        return Result.success(deptService.findAll());
    }

    /**
     * 跟据传递的id删除部门
     * @param id
     * @return Result对象
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据传递来的含name字段json新增部门
     * @param dept
     * @return Result对象
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }
}
