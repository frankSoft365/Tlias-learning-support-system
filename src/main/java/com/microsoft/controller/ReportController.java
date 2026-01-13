package com.microsoft.controller;

import com.microsoft.pojo.Result;
import com.microsoft.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 查询员工职位数量
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("查询员工职位数量");
        List<Map<String, Object>> list = reportService.getEmpJobData();
        return Result.success(list);
    }

    /**
     * 员工性别统计
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("员工性别统计");
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }
}
