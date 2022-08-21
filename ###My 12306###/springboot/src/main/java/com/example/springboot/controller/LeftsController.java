package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Lefts;
import com.example.springboot.service.ILeftsService;
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
@RequestMapping("/lefts")
public class LeftsController {
    @Resource
    private ILeftsService leftsService;
    @GetMapping
    public Result findAll() {
        return Result.success(leftsService.list());
    }
    @GetMapping("/{lid}")
    public Result findOne(@PathVariable Integer lid) {
        return Result.success(leftsService.getById(lid));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String sdate,
                           @RequestParam(defaultValue = "") String seattype,
                           @RequestParam(defaultValue = "") String tid,
                           @RequestParam(defaultValue = "") String stationId) {

        QueryWrapper<Lefts> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("lid");
        if (!"".equals(sdate)) {
            queryWrapper.like("sdate", sdate);
        }
        if (!"".equals(seattype)) {
            queryWrapper.like("seattype", seattype);
        }
        if (!"".equals(tid)) {
            queryWrapper.like("tid", tid);
        }
        if (!"".equals(stationId)) {
            queryWrapper.like("stationId", stationId);
        }

        return Result.success(leftsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @DeleteMapping("/{lid}")
    public Result delete(@PathVariable Integer lid) {
        leftsService.removeById(lid);
        return Result.success();
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        leftsService.removeByIds(ids);
        return Result.success();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Lefts lefts) {
        leftsService.saveOrUpdate(lefts);
        return Result.success();
    }

}

