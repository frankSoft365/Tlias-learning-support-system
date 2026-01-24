package com.microsoft.service;

import com.microsoft.pojo.LogInfoParam;
import com.microsoft.pojo.OperateLog;
import com.microsoft.pojo.PageResult;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 查询员工职位数量
     */
    List<Map<String, Object>> getEmpJobData();

    /**
     * 员工性别统计
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 班级人数统计
     */
    List<Map<String, Object>> getStuCountData();

    /**
     * 学员学历统计
     */
    List<Map<String, Object>> getStuDegreeData();

    /**
     *分页查询日志信息
     */
    PageResult<OperateLog> getLogInfo(LogInfoParam logInfoParam);
}
