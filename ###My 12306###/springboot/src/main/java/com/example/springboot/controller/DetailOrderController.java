package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.DetailOrder;
import com.example.springboot.service.IDetailOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ztt
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/detail-order")
public class DetailOrderController {
    @Resource
    private IDetailOrderService detailOrderService;
    @GetMapping
    public Result findAll() {
        return Result.success(detailOrderService.list());
    }
    @GetMapping("/{doid}")
    public Result findOne(@PathVariable Integer doid) {
        return Result.success(detailOrderService.getById(doid));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String date,
                           @RequestParam(defaultValue = "") String oid) {

        QueryWrapper<DetailOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("doid");
        if (!"".equals(date)) {
            queryWrapper.like("date", date);
        }
        if (!"".equals(oid)) {
            queryWrapper.like("oid", oid);
        }
        return Result.success(detailOrderService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @DeleteMapping("/{doid}")
    public Result delete(@PathVariable Integer doid) {
        detailOrderService.removeById(doid);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        detailOrderService.removeByIds(ids);
        return Result.success();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody DetailOrder detailOrder) {
        detailOrderService.saveOrUpdate(detailOrder);
        return Result.success();
    }
}

