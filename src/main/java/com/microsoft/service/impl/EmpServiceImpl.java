package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.mapper.EmpExprMapper;
import com.microsoft.mapper.EmpMapper;
import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpExpr;
import com.microsoft.pojo.EmpQueryParam;
import com.microsoft.pojo.PageResult;
import com.microsoft.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     * 分页查询员工列表
     * @param empQueryParam
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> rows = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    /**
     * 新增员工
     * @param emp
     */
    // 指定这是一个事务 在任何异常throw时回滚 保证添加员工基本信息与员工工作经历数据的一致性
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void add(Emp emp) {
        // 添加员工基本信息 除工作经历
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 批量添加员工的工作经历
        // 如果员工有工作经历就添加
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 给每个工作经历标注对应的员工id
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            // 批量添加员工工作经历
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 删除员工
     * @param ids
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 根据id删除emp表中的员工
        empMapper.delete(ids);
        // 根据id删除emp_expr表中的员工工作经历
        empExprMapper.delete(ids);
    }

    /**
     * 根据id查询员工的基本信息和工作经历
     * @param id
     * @return
     */
    @Override
    public Emp getEmpInfo(Integer id) {
        Emp emp = empMapper.getInfoById(id);
        List<EmpExpr> exprList = empExprMapper.getInfoById(id);
        emp.setExprList(exprList);
        return emp;
    }

    /**
     * 更改员工信息
     * @param emp
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 更改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        // 更改员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        // 如果更改后员工仍然有工作经历
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.upsert(exprList);
        }
    }
}
