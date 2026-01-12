package com.microsoft.service.impl;

import com.microsoft.mapper.EmpMapper;
import com.microsoft.pojo.JobOption;
import com.microsoft.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    /**
     * 查询员工职位数量
     * @return JobOption
     */
    @Override
    public List<JobOption> getEmpJobData() {
        List<JobOption> jobOptions = empMapper.countEmpJobData();
        return jobOptions;
    }
}
