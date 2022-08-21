package com.example.springboot.service.impl;

import com.example.springboot.entity.DetailTrain;
import com.example.springboot.mapper.DetailTrainMapper;
import com.example.springboot.service.IDetailTrainService;
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
public class DetailTrainServiceImpl extends ServiceImpl<DetailTrainMapper, DetailTrain> implements IDetailTrainService {

}
