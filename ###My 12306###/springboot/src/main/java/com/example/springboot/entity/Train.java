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
 * @since 2022-08-20
 */
@Getter
@Setter
@ApiModel(value = "Train对象", description = "")
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("列车编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("列车详情编号")
    private Integer dtid;

    @ApiModelProperty("列车名称")
    private String trainName;

    @ApiModelProperty("列车类型")
    private String trainType;

    @ApiModelProperty("列车长度")
    private String trainLength;

    @ApiModelProperty("车厢数量")
    private String carriageNum;


}
