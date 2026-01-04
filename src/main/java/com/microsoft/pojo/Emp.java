package com.microsoft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;                 // 员工id
    private String username;            // 用户名
    private String name;                // 姓名
    private Integer gender;             // 性别
    private String phone;               // 电话
    private Integer job;                // 职位
    private Integer salary;             // 薪资
    private LocalDate entryTime;        // 入职时期
    private String image;               // 头像
    private LocalDateTime createTime;   // 创建时间
    private LocalDateTime updateTime;   // 修改时间
    private String password;            // 密码
    private Integer deptId;             // 关联的部门id
    private String deptName;            // 部门名称
    private List<EmpExpr> exprList;     // 员工工作经历
}
