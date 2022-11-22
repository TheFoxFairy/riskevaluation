package com.gov.risk.indicator.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.risk.indicator.entity.CollingEntity;
import com.gov.risk.indicator.service.CollingService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;



/**
 * 指标依据表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@RestController
@RequestMapping("indicator/colling")
public class CollingController {
    @Autowired
    private CollingService collingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{collingId}")
    public R info(@PathVariable("collingId") Integer collingId){
		CollingEntity colling = collingService.getById(collingId);

        return R.ok().put("colling", colling);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CollingEntity colling){
		collingService.save(colling);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CollingEntity colling){
		collingService.updateById(colling);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] collingIds){
		collingService.removeByIds(Arrays.asList(collingIds));

        return R.ok();
    }

}
