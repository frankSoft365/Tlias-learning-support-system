package com.microsoft.controller;

import com.microsoft.pojo.Result;
import com.microsoft.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }
}
