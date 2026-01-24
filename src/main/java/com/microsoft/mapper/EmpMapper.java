package com.microsoft.mapper;

import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    /**
     * 向emp表中添加员工的基本信息 id、password不传递
     * @param emp
     */
    // 使用Options注解实现新增员工后主键id返回这个员工对象的id属性
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, entry_time, image, create_time, update_time, dept_id)" +
            " values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{entryTime}, #{image}, #{createTime}, #{updateTime}, #{deptId})")
    void insert(Emp emp);

    /**
     * 根据id删除员工的基本信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工基本信息和工作经历
     */
    Emp getInfoById(Integer id);

    /**
     * 更改员工信息 更新updateTime
     * @param emp
     */
    void update(Emp emp);

    /**
     * 查询员工职位数量
     * @return
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 员工性别统计
     * 为了不定义一个实体类 用map集合封装
     * 每个map集合 : {<"name" : "男性员工">, <"value" : "11"> }
     *              {<"name" : "女性员工">, <"value" : "4"> }
     * @return
     */
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工
     * @return
     */
    List<Emp> getAllEmp();

    /**
     * 根据部门id查询该部门下员工的数量
     */
    Integer countEmpInDept(Integer deptId);

    /**
     * 根据用户名和密码查询员工信息
     */
    Emp selectByUsernameAndPassword(Emp emp);
}
