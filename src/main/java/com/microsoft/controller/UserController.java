package com.microsoft.controller;

import com.microsoft.pojo.Emp;
import com.microsoft.pojo.Result;
import com.microsoft.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private EmpService empService;

    /**
     * 根据有效的token获取用户信息
     */
    @GetMapping("/user/profile")
    public Result getUserProfile(@RequestHeader("Authorization") String authHeader) {
        log.info("根据登录后的token获取用户个人信息");
        // 能访问此接口说明token有效
        String token = authHeader.substring(7);
        Emp emp = empService.getUserProfile(token);
        return Result.success(emp);
    }
}
