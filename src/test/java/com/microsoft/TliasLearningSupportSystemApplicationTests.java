package com.microsoft;

import com.microsoft.mapper.DeptMapper;
import com.microsoft.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TliasLearningSupportSystemApplicationTests {

    @Autowired
    private DeptMapper deptMapper;

}
