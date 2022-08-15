package com.qsr.login_test.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsr.login_test.common.Result;
import com.qsr.login_test.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;
import com.qsr.login_test.entity.Order;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderMapper orderMapper;
    //新增
    @PostMapping //post接口 前台可以把用户的json数据接收到 转成user类 然后通过insert这个方法实现用户新增
    public Result<?> save(@RequestBody Order order){ //把前台传过来的json转化成java对象 ?表示任何一种类型
        /**
         * @ResponseBody的作用其实是将java对象转为json格式的数据，然后直接写入HTTP response 的body中；一般在异步获取数据时使用 一直爆红
         * 实际上是自己enter按快了 应该使用@RequestBody
         */
        orderMapper.insert(order);// 把数据插入到数据库 mybatis提供的方法
        return Result.success();
    }

    //查询 分页查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, //当前页
                              @RequestParam(defaultValue = "10") Integer pageSize, //每页多少条
                              @RequestParam(defaultValue = "")  String search) //模糊查询
    {
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery(); //通过lambdaQuery生成一个wrapper对象
        if(StrUtil.isNotBlank(search)){ //引入hutool工具类 判断search不是空 避免nickName为空无法查出
            wrapper.like(Order::getOrderNumber,search); //模糊查询以昵称为准
        }
        Page<Order> orderPage = orderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(orderPage);
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Order order) {
        orderMapper.updateById(order);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        orderMapper.deleteById(id);
        return Result.success();
    }
}
