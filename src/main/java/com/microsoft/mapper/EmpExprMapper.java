package com.microsoft.mapper;

import com.microsoft.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工工作经历查询
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量添加员工的工作经历
     * @param exprList
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据id删除员工的工作经历
     * @param empIds
     */
    void delete(List<Integer> empIds);
}
