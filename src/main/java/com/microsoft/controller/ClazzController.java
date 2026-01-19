package com.microsoft.controller;

import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Result;
import com.microsoft.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 获取班级列表
     */
    @GetMapping("/clazzs")
    public Result getClazzList(ClazzQueryParam clazzQueryParam) {
        log.info("班级列表查询");
        PageResult<Clazz> result = clazzService.getList(clazzQueryParam);
        return Result.success(result);
    }

    /**
     * 新增班级
     */
    @PostMapping("/clazzs")
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增班级");
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级信息
     */
    @GetMapping("/clazzs/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id : {} 查询班级信息", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 更改班级信息
     */
    @PutMapping("/clazzs")
    public Result update(@RequestBody Clazz clazz) {
        log.info("更改班级信息");
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 根据id删除班级
     */
    @DeleteMapping("/clazzs/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除班级");
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 查询所有班级
     */
    @GetMapping("/clazzs/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }
}
