package com.gov.risk.auth.sys.controller;

import com.gov.risk.common.utils.R;
import com.gov.risk.auth.sys.entity.RolesEntity;
import com.gov.risk.auth.sys.entity.UsersEntity;
import com.gov.risk.auth.sys.service.RolesService;
import com.gov.risk.auth.sys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;



/**
 * 用户表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@RestController
@RequestMapping("sys/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public R list(@RequestParam Map<String, Object> params){
        List<UsersEntity> usersEntityList = usersService.listWithTree(params);
        return R.ok().put("data", usersEntityList);
    }

    @GetMapping( value = "/simple", produces = "application/json")
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
    @GetMapping( value = "/tree", produces = "application/json")
    public R info(@RequestParam Map<String, Object> userParams){
//

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
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public R save(@RequestBody UsersEntity users){
		usersService.save(users);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public R update(@RequestBody UsersEntity users){
		usersService.updateById(users);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public R delete(@RequestBody Integer[] userIds){
		usersService.removeByIds(Arrays.asList(userIds));
        return R.ok();
    }

}
