package com.microsoft.service;

import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpQueryParam;
import com.microsoft.pojo.LoginInfo;
import com.microsoft.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询、条件查询获取员工信息列表
     * @param empQueryParam
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * @param emp
     */
    void add(Emp emp);

    /**
     * 根据id删除员工
     * @param id
     */
    void delete(List<Integer> id);

    /**
     * 根据id查询回显员工基本信息和工作经历
     * @param id
     * @return
     */
    Emp getEmpInfo(Integer id);

    /**
     * 更改员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 查询所有员工
     * @return
     */
    List<Emp> list();

    /**
     * 员工登录校验
     */
    LoginInfo login(Emp emp);
}
