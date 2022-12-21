package com.gov.risk.algorithms.controller;

import java.util.Arrays;
import java.util.Map;

import com.gov.risk.algorithms.entity.ErrorIndexEntity;
import com.gov.risk.algorithms.service.ErrorIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;



/**
 * 指标依据与指标表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-12-21 19:20:01
 */
@RestController
@RequestMapping("indicator/errorindex")
public class ErrorIndexController {
    @Autowired
    private ErrorIndexService errorIndexService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = errorIndexService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{errorIndexId}")
    public R info(@PathVariable("errorIndexId") Integer errorIndexId){
		ErrorIndexEntity errorIndex = errorIndexService.getById(errorIndexId);

        return R.ok().put("errorIndex", errorIndex);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ErrorIndexEntity errorIndex){
		errorIndexService.save(errorIndex);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ErrorIndexEntity errorIndex){
		errorIndexService.updateById(errorIndex);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] errorIndexIds){
		errorIndexService.removeByIds(Arrays.asList(errorIndexIds));

        return R.ok();
    }

}
