package com.gov.risk.indicator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.risk.indicator.entity.IndexPropertyEntity;
import com.gov.risk.indicator.service.IndexPropertyService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;



/**
 * 指标属性表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@RestController
@RequestMapping("indicator/property")
public class IndexPropertyController {
    @Autowired
    private IndexPropertyService indexPropertyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = indexPropertyService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取所有标识，一个标识标识一个表
     */
    @RequestMapping("/identification")
    public R countIdentification(@RequestParam Map<String, Object> params){
        List<String> identifications = indexPropertyService.countIdentification(params);
        return R.ok().put("page", identifications);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{indexPropertyId}")
    public R info(@PathVariable("indexPropertyId") Integer indexPropertyId){
		IndexPropertyEntity indexProperty = indexPropertyService.getById(indexPropertyId);

        return R.ok().put("indexProperty", indexProperty);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody IndexPropertyEntity indexProperty){
		indexPropertyService.save(indexProperty);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody IndexPropertyEntity indexProperty){
		indexPropertyService.updateById(indexProperty);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] indexPropertyIds){
		indexPropertyService.removeByIds(Arrays.asList(indexPropertyIds));

        return R.ok();
    }

}
