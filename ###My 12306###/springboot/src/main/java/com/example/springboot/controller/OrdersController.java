package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Orders;
import com.example.springboot.mapper.OrdersMapper;
import com.example.springboot.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ztt
 * @since 2022-08-19
 */
@RestController
@RequestMapping("/orders")
//给接口加前缀
public class OrdersController {
    @Resource
    private IOrdersService ordersService;
    @GetMapping
    public Result findAll() {
        return Result.success(ordersService.list());
    }
    @GetMapping("/{oid}")
    public Result findOne(@PathVariable Integer oid) {
        return Result.success(ordersService.getById(oid));
    }

@GetMapping("/limit")
public Result limitRule(@RequestParam(defaultValue = "") String pid,
                        @RequestParam(defaultValue = "") String tid){
    QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
    if (!"".equals(pid)) {
        queryWrapper.eq("pid", pid);
    }
    if (!"".equals(tid)) {
        queryWrapper.eq("tid", tid);
    }
    return Result.success();
}


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String oid,
                           @RequestParam(defaultValue = "") String pid,
                           @RequestParam(defaultValue = "") String tid) {

        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("oid");
        if (!"".equals(oid)) {
            queryWrapper.like("oid", oid);
        }
        if (!"".equals(pid)) {
            queryWrapper.like("pid", pid);
        }
        if (!"".equals(tid)) {
            queryWrapper.like("tid", tid);
        }

        return Result.success(ordersService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @DeleteMapping("/{oid}")
    public Result delete(@PathVariable Integer oid) {
        ordersService.removeById(oid);
        return Result.success();
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ordersService.removeByIds(ids);
        return Result.success();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Orders orders) {
        ordersService.saveOrUpdate(orders);
        return Result.success();
    }
}

