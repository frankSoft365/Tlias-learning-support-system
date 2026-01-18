package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.exception.DataHasAssociatedRecordsException;
import com.microsoft.exception.DataNotFoundException;
import com.microsoft.mapper.ClazzMapper;
import com.microsoft.mapper.EmpMapper;
import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import com.microsoft.pojo.Emp;
import com.microsoft.pojo.PageResult;
import com.microsoft.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;

    /**
     * 获取班级列表
     */
    @Override
    public PageResult<Clazz> getList(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        // 判断班级状态
        LocalDate now = LocalDate.now();
        list.forEach(item -> {
            if (now.isAfter(item.getEndDate())) {
                item.setStatus("已结课");
            } else if (now.isBefore(item.getBeginDate())) {
                item.setStatus("未开班");
            } else {
                item.setStatus("在读中");
            }
        });
        Page<Clazz> p = (Page<Clazz>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 新增班级
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID查询班级信息
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 更改班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Clazz clazz) {
        // 先查询该班级是否存在
        Clazz clazzMapperById = clazzMapper.getById(clazz.getId());
        if (clazzMapperById == null) {
            throw new DataNotFoundException("该班级已经不存在！不能进行修改！");
        }
        // 关联了员工表的班主任id：masterId 检查该员工是否存在
        Emp infoById = empMapper.getInfoById(clazz.getMasterId());
        if (infoById == null) {
            throw new DataNotFoundException("该班主任已经不存在！不能更改班级信息！");
        }
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * 根据id删除班级
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Integer id) {
        // 如果该班级下面有学员 则不能删除该班级
        Integer count = clazzMapper.countStudentInClazz(id);
        if (count > 0) {
            throw new DataHasAssociatedRecordsException("对不起, 该班级下有学生, 不能直接删除");
        }
        // 否则 删除班级
        clazzMapper.delete(id);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> list() {
        return clazzMapper.getAllClazz();
    }
}
