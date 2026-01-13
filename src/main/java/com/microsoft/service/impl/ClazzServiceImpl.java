package com.microsoft.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.mapper.ClazzMapper;
import com.microsoft.pojo.Clazz;
import com.microsoft.pojo.ClazzQueryParam;
import com.microsoft.pojo.PageResult;
import com.microsoft.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    /**
     * 获取班级列表
     * @param clazzQueryParam
     * @return
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
     * @param clazz
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }
}
