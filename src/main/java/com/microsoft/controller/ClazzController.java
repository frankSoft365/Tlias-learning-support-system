package com.microsoft.controller;

import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Result;
import com.microsoft.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 获取班级列表
     * @param clazzQueryParam
     * @return
     */
    @GetMapping("/clazzs")
    public Result getClazzList(ClazzQueryParam clazzQueryParam) {
        log.info("班级列表查询");
        PageResult<Clazz> result = clazzService.getList(clazzQueryParam);
        return Result.success(result);
    }
}
