package com.microsoft.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 查询员工职位数量
     *
     * @return
     */
    List<Map<String, Object>> getEmpJobData();

    /**
     * 员工性别统计
     *
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();
}
