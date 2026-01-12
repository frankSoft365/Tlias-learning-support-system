package com.microsoft.service;

import com.microsoft.pojo.GenderOption;
import com.microsoft.pojo.JobOption;

import java.util.List;

public interface ReportService {
    /**
     * 查询员工职位数量
     * @return
     */
    List<JobOption> getEmpJobData();

    /**
     * 员工性别统计
     * @return
     */
    List<GenderOption> getEmpGenderData();
}
