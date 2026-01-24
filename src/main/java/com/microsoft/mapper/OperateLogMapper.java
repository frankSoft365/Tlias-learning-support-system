package com.microsoft.mapper;

import com.microsoft.pojo.LogInfoParam;
import com.microsoft.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {
    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    /**
     * 查询operate_log表中所有数据多表查询员工的姓名
     */
    @Select("select o.*, e.name as operateEmpName from operate_log o left join emp e on o.operate_emp_id = e.id order by o.operate_time desc")
    List<OperateLog> list();
}
