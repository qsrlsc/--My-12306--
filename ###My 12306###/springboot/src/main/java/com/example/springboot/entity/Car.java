package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("train")
public class Car {
    @TableId(type = IdType.AUTO)
    private Integer tid;
    private Integer dtid;
    private String trainName;
    private String trainType;
    private String trainLength;
    private String carriageNum;
}
