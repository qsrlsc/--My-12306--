package com.example.springboot.service.impl;

import com.example.springboot.entity.DetailOrder;
import com.example.springboot.mapper.DetailOrderMapper;
import com.example.springboot.service.IDetailOrderService;
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
public class DetailOrderServiceImpl extends ServiceImpl<DetailOrderMapper, DetailOrder> implements IDetailOrderService {

}
