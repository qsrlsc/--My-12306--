package com.qsr.login_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("staus")
@Data
public class CarStaus {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String carNumber;
    private String carStaus;
    private String carTstaus;
}
