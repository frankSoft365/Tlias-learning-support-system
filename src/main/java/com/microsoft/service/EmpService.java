package com.microsoft.service;

import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpQueryParam;
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
}
