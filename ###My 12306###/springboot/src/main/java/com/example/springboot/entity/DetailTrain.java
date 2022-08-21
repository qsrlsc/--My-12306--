package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2022-08-20
 */
@Getter
@Setter
@TableName("detail_train")
@ApiModel(value = "DetailTrain对象", description = "")
public class DetailTrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单详情编号")
    @TableId(value = "dtid", type = IdType.AUTO)
    private Integer dtid;

    @ApiModelProperty("列车编号")
    private Integer tid;

    @ApiModelProperty("列车类型")
    private String ttype;

    @ApiModelProperty("始发站")
    private String origin;

    @ApiModelProperty("终点站")
    private String destination;

    @ApiModelProperty("出发时间")
    private LocalDateTime starttime;

    @ApiModelProperty("到达时间")
    private LocalDateTime arrivetime;

    @ApiModelProperty("里程")
    private String mileage;


}
