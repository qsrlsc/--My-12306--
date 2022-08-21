package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
@TableName("detail_order")
@ApiModel(value = "DetailOrder对象", description = "")
public class DetailOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单状态编号")
    @TableId(value = "doid", type = IdType.AUTO)
    private Integer doid;

    @ApiModelProperty("订单编号")
    private Integer oid;

    @ApiModelProperty("下单日期")
    private LocalDate date;

    @ApiModelProperty("下单时间")
    private LocalTime createAt;

    @ApiModelProperty("订单状态")
    private String status;


}
