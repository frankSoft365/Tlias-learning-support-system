package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.mapper.EmpMapper;
import com.microsoft.mapper.OperateLogMapper;
import com.microsoft.mapper.StudentMapper;
import com.microsoft.pojo.LogInfoParam;
import com.microsoft.pojo.OperateLog;
import com.microsoft.pojo.PageResult;
import com.microsoft.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 查询员工职位数量
     * @return JobOption
     */
    @Override
    public List<Map<String, Object>> getEmpJobData() {
        return empMapper.countEmpJobData();
    }

    /**
     * 员工性别统计
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /**
     * 班级人数统计
     */
    @Override
    public List<Map<String, Object>> getStuCountData() {
        return studentMapper.countStudentInClazz();
    }

    /**
     * 学员学历统计
     */
    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return studentMapper.countStuDegree();
    }

    /**
     * 分页查询日志信息
     * @param logInfoParam
     * @return
     */
    @Override
    public PageResult<OperateLog> getLogInfo(LogInfoParam logInfoParam) {
        PageHelper.startPage(logInfoParam.getPage(), logInfoParam.getPageSize());
        List<OperateLog> list = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
