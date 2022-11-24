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

import com.gov.risk.indicator.entity.IndexesEntity;
import com.gov.risk.indicator.service.IndexesService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;



/**
 * 指标表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@RestController
@RequestMapping("indicator/indexes")
public class IndexesController {
    @Autowired
    private IndexesService indexesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = indexesService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/tree")
    public R listTree(@RequestParam Map<String, Object> params){
        List<List<IndexesEntity>> indexesEntities = (List<List<IndexesEntity>>) indexesService.listWithTree(params);

        return R.ok().put("data", indexesEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{indexId}")
    public R info(@PathVariable("indexId") Integer indexId){
		IndexesEntity indexes = indexesService.getById(indexId);

        return R.ok().put("indexes", indexes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody IndexesEntity indexes){
		indexesService.save(indexes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody IndexesEntity indexes){
		indexesService.updateById(indexes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] indexIds){
		indexesService.removeByIds(Arrays.asList(indexIds));

        return R.ok();
    }

}
