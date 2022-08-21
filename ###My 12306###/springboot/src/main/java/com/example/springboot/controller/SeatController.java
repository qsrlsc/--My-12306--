package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Seat;
import com.example.springboot.service.ISeatService;
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
@RequestMapping("/seat")
public class SeatController {
    @Resource
    private ISeatService seatService;

    @GetMapping
    public Result findAll() {
        return Result.success(seatService.list());
    }
    @GetMapping("/{sid}")
    public Result findOne(@PathVariable Integer sid) {
        return Result.success(seatService.getById(sid));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String carriage,
                           @RequestParam(defaultValue = "") String seatlocation,
                           @RequestParam(defaultValue = "") String seattype) {

        QueryWrapper<Seat> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sid");
        if (!"".equals(carriage)) {
            queryWrapper.like("carriage", carriage);
        }
        if (!"".equals(seatlocation)) {
            queryWrapper.like("seatlocation", seatlocation);
        }
        if (!"".equals(seattype)) {
            queryWrapper.like("seattype", seattype);
        }

        return Result.success(seatService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @DeleteMapping("/{sid}")
    public Result delete(@PathVariable Integer sid) {
        seatService.removeById(sid);
        return Result.success();
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        seatService.removeByIds(ids);
        return Result.success();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Seat seat) {
        seatService.saveOrUpdate(seat);
        return Result.success();
    }
}

