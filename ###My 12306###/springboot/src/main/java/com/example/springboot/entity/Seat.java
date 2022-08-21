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
@ApiModel(value = "Seat对象", description = "")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("座位编号")
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    @ApiModelProperty("列车编号")
    private Integer tid;

    @ApiModelProperty("座位类型")
    private String seattype;

    @ApiModelProperty("座位位置")
    private String seatlocation;

    @ApiModelProperty("座位所在车厢")
    private String carriage;


}
