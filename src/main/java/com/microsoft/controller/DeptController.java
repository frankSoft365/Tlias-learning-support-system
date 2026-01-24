package com.microsoft.controller;

import com.microsoft.anno.LogOperation;
import com.microsoft.pojo.Dept;
import com.microsoft.pojo.Result;
import com.microsoft.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理
 */
@Slf4j
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
        log.info("查询部门列表");
        return Result.success(deptService.findAll());
    }

    /**
     * 跟据传递的id删除部门
     * @param id
     * @return Result对象
     */
    @LogOperation
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        log.info("要删除的部门 : {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据传递来的含name字段json新增部门
     * @param dept
     * @return Result对象
     */
    @LogOperation
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("新增的部门的name : {}", dept.getName());
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据路径id查询部门
     * @param id
     * @return Result对象
     */
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询回显部门id : {}", id);
        Dept dept = deptService.getInfoById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门名称
     * @param dept
     * @return Result对象
     */
    @LogOperation
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门");
        deptService.update(dept);
        return Result.success();
    }
}
