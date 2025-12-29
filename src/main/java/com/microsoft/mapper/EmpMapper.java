package com.microsoft.mapper;

import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工查询
 */
@Mapper
public interface EmpMapper {
    /**
     * 分页查询列表
     * @return List集合，每个元素是Emp对象
     */
    List<Emp> list(EmpQueryParam empQueryParam);
}
