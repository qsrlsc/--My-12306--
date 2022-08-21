package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ztt
 * @since 2022-08-19
 */
@Getter
@Setter
@ApiModel(value = "Lefts对象", description = "")
public class Lefts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("余票表编号")
    @TableId(value = "lid", type = IdType.AUTO)
    private Integer lid;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty("日期")
    private LocalDate sdate;

    @ApiModelProperty("座位类型")
    private String seattype;

    @ApiModelProperty("车站编号")
    private Integer stationId;

    @ApiModelProperty("列车编号")
    private Integer tid;

    @ApiModelProperty("剩余票数")
    private Integer leftnum;


}
