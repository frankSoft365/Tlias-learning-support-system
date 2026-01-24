package com.microsoft.controller;

import com.microsoft.pojo.LogInfoParam;
import com.microsoft.pojo.OperateLog;
import com.microsoft.pojo.PageResult;
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
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


    /**
     * 查询员工职位数量
     */
    @GetMapping("/report/empJobData")
    public Result getEmpJobData() {
        log.info("查询员工职位数量");
        List<Map<String, Object>> list = reportService.getEmpJobData();
        return Result.success(list);
    }

    /**
     * 员工性别统计
     */
    @GetMapping("/report/empGenderData")
    public Result getEmpGenderData() {
        log.info("员工性别统计");
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/report/studentCountData")
    public Result getStuCountData() {
        log.info("班级人数统计");
        List<Map<String, Object>> list = reportService.getStuCountData();
        return Result.success(list);
    }

    /**
     * 学员学历统计
     */
    @GetMapping("/report/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("学员学历统计");
        List<Map<String, Object>> list = reportService.getStuDegreeData();
        return Result.success(list);
    }

    /**
     *分页查询操作日志
     */
    @GetMapping("/log/page")
    public Result getLogInfo(LogInfoParam logInfoParam) {
        log.info("日志信息查询");
        PageResult<OperateLog> pageResult = reportService.getLogInfo(logInfoParam);
        return Result.success(pageResult);
    }
}
