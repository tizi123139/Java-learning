package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Student {
    private Integer id; //ID
    private String name; //姓名
    private String no; //序号
    private Integer gender; //性别 , 1: 男 , 2 : 女
    private String phone; //手机号
    private String idCard; //身份证号
    private Integer isCollege; //是否来自于院校, 1: 是, 0: 否
    private String address; //联系地址
    private Integer degree; //最高学历, 1: 初中, 2: 高中 , 3: 大专 , 4: 本科 , 5: 硕士 , 6: 博士
    private LocalDate graduationDate; //毕业时间
    private Integer clazzId; //班级ID
    private Short violationCount; //违纪次数
    private Short violationScore; //违纪扣分
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    private String clazzName;//班级名称

    // 1. 无参构造（替代 @NoArgsConstructor）
    public Student() {
    }

    // 2. 全参构造（包含所有字段）
    public Student(Integer id, String name, String no, Integer gender, String phone,
                   String idCard, Integer isCollege, String address, Integer degree,
                   LocalDate graduationDate, Integer clazzId, Short violationCount,
                   Short violationScore, LocalDateTime createTime, LocalDateTime updateTime,
                   String clazzName) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.gender = gender;
        this.phone = phone;
        this.idCard = idCard;
        this.isCollege = isCollege;
        this.address = address;
        this.degree = degree;
        this.graduationDate = graduationDate;
        this.clazzId = clazzId;
        this.violationCount = violationCount;
        this.violationScore = violationScore;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.clazzName = clazzName;
    }
}