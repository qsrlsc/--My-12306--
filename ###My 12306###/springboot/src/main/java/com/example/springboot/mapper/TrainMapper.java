package com.example.springboot.mapper;

import com.example.springboot.entity.Train;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ztt
 * @since 2022-08-20
 */
@Mapper
public interface TrainMapper extends BaseMapper<Train> {

}
