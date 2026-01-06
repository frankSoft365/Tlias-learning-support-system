package com.microsoft.mapper;

import com.microsoft.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 根据empId查询员工工作经历 封装到集合中
     * @param empId
     * @return
     */
    @Select("select id, begin, end, company, job, emp_id from emp_expr where emp_id = #{empId}")
    List<EmpExpr> getInfoById(Integer empId);

    /**
     * 直接更改对应id的工作经历 如果没有这个工作经历id，则是新的工作经历，根据empId将新的工作经历添加到emp_expr表
     * @param exprList
     */
    void upsert(List<EmpExpr> exprList);
}
