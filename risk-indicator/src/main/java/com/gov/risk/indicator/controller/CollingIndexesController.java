//package com.gov.risk.indicator.controller;
//
//import java.util.Arrays;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gov.risk.indicator.entity.CollingIndexesEntity;
//import com.gov.risk.indicator.service.CollingIndexesService;
//import com.gov.risk.common.utils.PageUtils;
//import com.gov.risk.common.utils.R;
//
//
//
///**
// * 指标依据与指标表
// *
// * @author ThrFoxFairy
// * @email onlytokimeki@gmail.com
// * @date 2022-11-19 17:06:37
// */
//@RestController
//@RequestMapping("indicator/collingindexes")
//public class CollingIndexesController {
//    @Autowired
//    private CollingIndexesService collingIndexesService;
//
//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = collingIndexesService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{collingId}")
//    public R info(@PathVariable("collingId") Integer collingId){
//		CollingIndexesEntity collingIndexes = collingIndexesService.getById(collingId);
//
//        return R.ok().put("collingIndexes", collingIndexes);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody CollingIndexesEntity collingIndexes){
//		collingIndexesService.save(collingIndexes);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody CollingIndexesEntity collingIndexes){
//		collingIndexesService.updateById(collingIndexes);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] collingIds){
//		collingIndexesService.removeByIds(Arrays.asList(collingIds));
//
//        return R.ok();
//    }
//
//}
