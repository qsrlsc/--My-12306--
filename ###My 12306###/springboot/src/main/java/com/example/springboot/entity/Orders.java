package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单编号")
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    @ApiModelProperty("乘客编号")
    private Integer pid;

    @ApiModelProperty("列车编号")
    private Integer tid;

    @ApiModelProperty("用户编号")
    private Integer uid;

    @ApiModelProperty("订单详情编号")
    private Integer doid;

    @ApiModelProperty("有无额外产品")
    private String extraProduct;

    @ApiModelProperty("有无保险")
    private String insurance;

    @ApiModelProperty("折扣")
    private String discount;


}
