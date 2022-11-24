package com.gov.risk.auth.sys.controller;

import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;
import com.gov.risk.auth.sys.entity.RolePowerEntity;
import com.gov.risk.auth.sys.service.RolePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 角色权限（关系）表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@RestController
@RequestMapping("sys/rolepower")
public class RolePowerController {
    @Autowired
    private RolePowerService rolePowerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rolePowerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    public R info(@PathVariable("roleId") Integer roleId){
		RolePowerEntity rolePower = rolePowerService.getById(roleId);

        return R.ok().put("rolePower", rolePower);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RolePowerEntity rolePower){
		rolePowerService.save(rolePower);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RolePowerEntity rolePower){
		rolePowerService.updateById(rolePower);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] roleIds){
		rolePowerService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}
