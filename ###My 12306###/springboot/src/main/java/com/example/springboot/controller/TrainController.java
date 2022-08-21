package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Train;
import com.example.springboot.service.ITrainService;
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
@RequestMapping("/train")
public class TrainController {
    @Resource
    private ITrainService trainService;
    @GetMapping
    public Result findAll() {
        return Result.success(trainService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(trainService.getById(id));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String trainName) {

        QueryWrapper<Train> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(trainName)) {
            queryWrapper.like("train_name", trainName);
        }

        return Result.success(trainService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        trainService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        trainService.removeByIds(ids);
        return Result.success();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Train train) {
        trainService.saveOrUpdate(train);
        return Result.success();
    }
}

