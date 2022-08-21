package com.example.springboot.service.impl;

import com.example.springboot.entity.Seat;
import com.example.springboot.mapper.SeatMapper;
import com.example.springboot.service.ISeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ztt
 * @since 2022-08-19
 */
@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements ISeatService {

}
