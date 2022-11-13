package com.gov.risk.auth.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gov.risk.auth.sys.entity.RolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.risk.auth.sys.service.RolesService;
import com.gov.risk.common.utils.R;



/**
 * 角色表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@RestController
@RequestMapping("auth/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){

        List<RolesEntity> rolesEntityList = rolesService.listWithTree(params);

        return R.ok().put("data", rolesEntityList);
    }

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = rolesService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    public R info(@PathVariable("roleId") Integer roleId){


        Map<String, Object> params = new HashMap<>();

        params.put("roleId",roleId);
        List<RolesEntity> roles = rolesService.listWithTree(params);

        RolesEntity role = roles.size() == 1?roles.get(0):null;

//		RolesEntity roles = rolesService.getById(roleId);
        return R.ok().put("roles", role);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RolesEntity roles){
		rolesService.save(roles);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RolesEntity roles){
		rolesService.updateById(roles);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] roleIds){
		rolesService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}
