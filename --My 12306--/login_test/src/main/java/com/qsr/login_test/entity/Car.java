package com.qsr.login_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("car")
@Data
public class Car {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String carNumber;
    private String carStyle;
    private String carTime;
    private String carAddress;
}