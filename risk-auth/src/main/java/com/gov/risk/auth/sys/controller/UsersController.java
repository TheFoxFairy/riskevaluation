package com.gov.risk.auth.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import com.gov.risk.auth.sys.entity.RolesEntity;
import com.gov.risk.auth.sys.entity.UsersEntity;
import com.gov.risk.auth.sys.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.risk.auth.sys.service.UsersService;
import com.gov.risk.common.utils.R;



/**
 * 用户表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@RestController
@RequestMapping("auth/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<UsersEntity> usersEntityList = usersService.listWithTree(params);
        return R.ok().put("data", usersEntityList);
    }

    @RequestMapping("/list/simple")
    public R listSimple(@RequestParam Map<String, Object> params){
        HashMap<String, CopyOnWriteArraySet<Object>> urlMap =  usersService.listWithTree2(params);

        return R.ok().put("data", urlMap);
    }

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")ct
//    public R list(@RequestParam Map<String, Obje> params){
//        PageUtils page = usersService.queryPage(params);
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){

        Map<String, Object> userParams = new HashMap<>();
        userParams.put("userId",userId);

        List<UsersEntity> usersEntityList = usersService.listWithTree(userParams);
		UsersEntity users = usersEntityList.size() == 1?usersEntityList.get(0):null;

        if(users != null){
            Map<String, Object> roleParams = new HashMap<>();
            roleParams.put("roleId",users.getRole().getRoleId());
            List<RolesEntity> roles = rolesService.listWithTree(roleParams);
            RolesEntity role = roles.size() == 1?roles.get(0):null;
            users.setRole(role);
        }
        return R.ok().put("users", users);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsersEntity users){
		usersService.save(users);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UsersEntity users){
		usersService.updateById(users);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] userIds){
		usersService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
