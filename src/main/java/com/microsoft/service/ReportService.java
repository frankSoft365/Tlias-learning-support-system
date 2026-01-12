package com.microsoft.service;

import com.microsoft.pojo.JobOption;

import java.util.List;

public interface ReportService {
    /**
     * 查询员工职位数量
     * @return
     */
    List<JobOption> getEmpJobData();
}
