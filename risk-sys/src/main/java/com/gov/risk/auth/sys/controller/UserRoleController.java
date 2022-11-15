package com.gov.risk.auth.sys.controller;

import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;
import com.gov.risk.auth.sys.entity.UserRoleEntity;
import com.gov.risk.auth.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 用户角色（关系）表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@RestController
@RequestMapping("sys/userrole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		UserRoleEntity userRole = userRoleService.getById(userId);

        return R.ok().put("userRole", userRole);
    }

    /**
     * 保存
     */
//    @RequestMapping("/save")
    @RequestMapping(method = RequestMethod.POST)
    public R save(@RequestBody UserRoleEntity userRole){
		userRoleService.save(userRole);

        return R.ok();
    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
    @RequestMapping(method = RequestMethod.PUT)
    public R update(@RequestBody UserRoleEntity userRole){
		userRoleService.updateById(userRole);

        return R.ok();
    }

    /**
     * 删除
     */
//    @RequestMapping("/delete")
    @RequestMapping(method = RequestMethod.DELETE)
    public R delete(@RequestBody Integer[] userIds){
		userRoleService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
