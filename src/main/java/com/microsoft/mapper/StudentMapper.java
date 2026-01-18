package com.microsoft.mapper;

import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student
     */
    void insert(Student student);

    /**
     * 根据id查询学员信息
     * @param id
     * @return
     */
    Student getInfoById(Integer id);

    /**
     * 修改学员
     * @param student
     */
    void update(Student student);

    /**
     * 根据id删除学员
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 统计班级人数
     * @return
     */
    List<Map<String, Object>> countStudentInClazz();

    /**
     * 学员学历统计
     * @return
     */
    List<Map<String, Object>> countStuDegree();
}
