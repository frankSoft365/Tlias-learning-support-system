package com.microsoft.mapper;

import com.microsoft.pojo.Emp;
import com.microsoft.pojo.EmpQueryParam;
import com.microsoft.pojo.GenderOption;
import com.microsoft.pojo.JobOption;
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
     * @param id
     * @return
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
    List<JobOption> countEmpJobData();

    /**
     * 员工性别统计
     * @return
     */
    List<GenderOption> countEmpGenderData();
}
