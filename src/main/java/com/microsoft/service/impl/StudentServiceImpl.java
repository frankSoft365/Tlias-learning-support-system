package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.exception.MaxValueExceededException;
import com.microsoft.mapper.StudentMapper;
import com.microsoft.pojo.PageResult;
import com.microsoft.pojo.Student;
import com.microsoft.pojo.StudentQueryParam;
import com.microsoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 学员列表查询
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> list = studentMapper.page(studentQueryParam);
        Page<Student> p = (Page<Student>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 新增学员
     * @param student
     */
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    /**
     * 根据id查询学员信息
     * @param id
     * @return
     */
    @Override
    public Student getInfoById(Integer id) {
        return studentMapper.getInfoById(id);
    }

    /**
     * 修改学员
     * @param student
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 根据id删除学员 直接删除 无需考虑其他表的关联
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /**
     * 违纪处理 违纪处理一次，需要将违纪次数+1，违纪扣分+前端输入的分数。
     * @param id
     * @param score
     */
    @Override
    public void violationAction(Integer id, Integer score) {
        // 根据id获取该学员的信息 包含违纪次数和违纪扣分
        Student stuInfo = studentMapper.getInfoById(id);
        // 违纪次数加1
        // 获取违纪次数
        Short violationCount = stuInfo.getViolationCount();
        if (violationCount == Short.MAX_VALUE) {
            // 抛出数值达到最大限度无法继续增加异常
            throw new MaxValueExceededException("不能再增加违纪次数！");
        }
        // 违纪次数加1
        violationCount = (short) (violationCount + 1);
        stuInfo.setViolationCount(violationCount);
        // 违纪扣分增加
        Short violationScore = stuInfo.getViolationScore();
        if (violationScore + score > Short.MAX_VALUE) {
            throw new MaxValueExceededException("违纪扣分过大，不能再增加违纪扣分！");
        }
        violationScore = (short) (violationScore + score);
        stuInfo.setViolationScore(violationScore);
        // 更新修改时间
        stuInfo.setUpdateTime(LocalDateTime.now());
        // 将新的学生信息更新到数据库
        studentMapper.update(stuInfo);
    }
}
