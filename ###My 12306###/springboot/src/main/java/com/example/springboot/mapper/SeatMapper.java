package com.example.springboot.mapper;

import com.example.springboot.entity.Seat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ztt
 * @since 2022-08-19
 */
@Mapper
public interface SeatMapper extends BaseMapper<Seat> {

}
