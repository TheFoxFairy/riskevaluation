package com.gov.risk.auth.sys.controller;

import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;
import com.gov.risk.auth.sys.entity.PowersEntity;
import com.gov.risk.auth.sys.service.PowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 权限表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@RestController
@RequestMapping("sys/powers")
public class PowersController {
    @Autowired
    private PowersService powersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = powersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{powerId}")
    public R info(@PathVariable("powerId") Integer powerId){
		PowersEntity powers = powersService.getById(powerId);

        return R.ok().put("powers", powers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PowersEntity powers){
		powersService.save(powers);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PowersEntity powers){
		powersService.updateById(powers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] powerIds){
		powersService.removeByIds(Arrays.asList(powerIds));

        return R.ok();
    }

}
