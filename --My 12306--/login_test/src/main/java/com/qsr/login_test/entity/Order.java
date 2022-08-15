package com.qsr.login_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("order1")
@Data
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String orderNumber;
    private String traller;
    private String carNumber;
    private String sitStyle;
    private String carAddress;
    private String orderMoney;
    private String sitNumber;
    private String orderStyle;
}
