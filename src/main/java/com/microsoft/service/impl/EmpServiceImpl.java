package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.exception.DataHasAssociatedRecordsException;
import com.microsoft.exception.DataNotFoundException;
import com.microsoft.mapper.ClazzMapper;
import com.microsoft.mapper.DeptMapper;
import com.microsoft.mapper.EmpExprMapper;
import com.microsoft.mapper.EmpMapper;
import com.microsoft.pojo.*;
import com.microsoft.service.EmpService;
import com.microsoft.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询员工列表
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 将页码、每页数据数量交给PageHelper，他会自动生成查询总列表数量和分页的sql语句
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> rows = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 新增员工
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
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 检查是否有班级以该员工为班主任
        ids.forEach(id -> {
            Integer clazzCount = clazzMapper.getByMasterId(id);
            if (clazzCount > 0) {
                throw new DataHasAssociatedRecordsException("员工关联了" + clazzCount + "个班级，无法删除!");
            }
        });
        // 根据id删除emp表中的员工
        empMapper.delete(ids);
        // 根据id删除emp_expr表中的员工工作经历
        empExprMapper.delete(ids);
    }

    /**
     * 根据id查询员工的基本信息和工作经历
     */
    @Override
    public Emp getEmpInfo(Integer id) {
        return empMapper.getInfoById(id);
    }

    /**
     * 更改员工信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 检查该员工是否存在
        Emp infoById1 = empMapper.getInfoById(emp.getId());
        if (infoById1 == null) {
            throw new DataNotFoundException("该员工已经不存在！不能进行修改！");
        }
        // 涉及修改部门 检查该部门是否存在
        Dept infoById = deptMapper.getInfoById(emp.getDeptId());
        if (infoById == null) {
            throw new DataNotFoundException("你修改为的所属部门，该部门已经不存在！无法修改！");
        }
        // 更改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        // 更改员工工作经历
        // 先删除员工原有的工作经历
        empExprMapper.delete(Arrays.asList(emp.getId()));
        // 获取员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        // 如果更改后员工仍然有工作经历
        if (!CollectionUtils.isEmpty(exprList)) {
            // 新的工作经历没有empId字段 为其添加
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 查询所有员工
     */
    @Override
    public List<Emp> list() {
        return empMapper.getAllEmp();
    }

    /**
     * 登录校验
     */
    @Override
    public LoginInfo login(Emp emp) {
        // 根据emp中的用户名和密码查询员工信息，查询到则返回emp对象，没有查询到则是null
        Emp info = empMapper.selectByUsernameAndPassword(emp);
        if (info != null) {
            log.info("登录成功，用户信息：{}", info);
            // 封装token信息
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", info.getId());
            dataMap.put("username", info.getUsername());
            // 获取token
            String token = JwtUtils.generateToken(dataMap);
            // 封装到LoginInfo对象
            return new LoginInfo(info.getId(), info.getUsername(), info.getName(), token);
        }
        return null;
    }
}
