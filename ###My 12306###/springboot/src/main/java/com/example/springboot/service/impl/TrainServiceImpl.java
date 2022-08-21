package com.example.springboot.service.impl;

import com.example.springboot.entity.Train;
import com.example.springboot.mapper.TrainMapper;
import com.example.springboot.service.ITrainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ztt
 * @since 2022-08-20
 */
@Service
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements ITrainService {

}
