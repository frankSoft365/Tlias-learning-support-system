package com.microsoft.service.impl;

import com.microsoft.exception.DataHasAssociatedRecordsException;
import com.microsoft.exception.DataNotFoundException;
import com.microsoft.mapper.DeptMapper;
import com.microsoft.mapper.EmpMapper;
import com.microsoft.pojo.Dept;
import com.microsoft.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 删除部门 需要检查部门下是否有员工，如果有，删除失败，抛出异常
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(Integer id) {
        // 检查该部门下是否有员工
        Integer count = empMapper.countEmpInDept(id);
        if (count > 0) {
            throw new DataHasAssociatedRecordsException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    /**
     * 修改部门
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Dept dept) {
        // 检查该部门是否存在
        Dept infoById = deptMapper.getInfoById(dept.getId());
        if (infoById == null) {
            throw new DataNotFoundException("该部门已经不存在！不能进行修改！");
        }
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept getInfoById(Integer id) {
        return deptMapper.getInfoById(id);
    }
}