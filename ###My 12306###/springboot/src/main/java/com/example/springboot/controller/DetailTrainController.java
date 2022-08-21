package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.DetailTrain;
import com.example.springboot.service.IDetailTrainService;
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
@RequestMapping("/detail-train")
public class DetailTrainController {
    @Resource
    private IDetailTrainService detailTrainService;
    @GetMapping
    public Result findAll() {
        return Result.success(detailTrainService.list());
    }
    @GetMapping("/{dtid}")
    public Result findOne(@PathVariable Integer dtid) {
        return Result.success(detailTrainService.getById(dtid));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String dtid,
                           @RequestParam(defaultValue = "") String tid,
                           @RequestParam(defaultValue = "") String ttype) {

        QueryWrapper<DetailTrain> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("dtid");
        if (!"".equals(dtid)) {
            queryWrapper.like("dtid", dtid);
        }
        if (!"".equals(tid)) {
            queryWrapper.like("tid", tid);
        }
        if (!"".equals(ttype)) {
            queryWrapper.like("ttype", ttype);
        }
        return Result.success(detailTrainService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @DeleteMapping("/{dtid}")
    public Result delete(@PathVariable Integer dtid) {
        detailTrainService.removeById(dtid);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        detailTrainService.removeByIds(ids);
        return Result.success();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody DetailTrain detailTrain) {
        detailTrainService.saveOrUpdate(detailTrain);
        return Result.success();
    }
}

