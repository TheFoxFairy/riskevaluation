package com.gov.risk.auth.sys.service.impl;

import com.gov.risk.auth.sys.dao.UsersDao;
import com.gov.risk.auth.sys.entity.PowersEntity;
import com.gov.risk.auth.sys.entity.RolesEntity;
import com.gov.risk.auth.sys.entity.UsersEntity;
import com.gov.risk.auth.sys.service.RolesService;
import com.gov.risk.auth.sys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;


@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity> implements UsersService {

    @Autowired
    RolesService rolesService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersEntity> page = this.page(
                new Query<UsersEntity>().getPage(params),
                new QueryWrapper<UsersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<UsersEntity> listWithTree(Map<String, Object> params) {

        Object powerType = params.getOrDefault("powerType",null);
        params.remove("powerType");

        List<UsersEntity> usersEntityList = this.baseMapper.listWithTree(params);

        for(int i=0;i < usersEntityList.size();i++) {

            UsersEntity usersEntity = usersEntityList.get(i);
            Map<String, Object> roleParams = new HashMap<>();
            roleParams.put("roleId", usersEntity.getRole().getRoleId());
            if(powerType != null)
                roleParams.put("powerType", powerType.toString());
            List<RolesEntity> roles = rolesService.listWithTree(roleParams);
            RolesEntity role = roles.size() == 1 ? roles.get(0) : null;

            usersEntity.setRole(role);
            usersEntityList.set(i,usersEntity);
        }

        return usersEntityList;
    }

    @Override
    public HashMap<String, CopyOnWriteArraySet<Object>> listWithTree2(Map<String, Object> params) {

        HashMap<String, CopyOnWriteArraySet<Object>> urlMap = new HashMap<String, CopyOnWriteArraySet<Object>>();
        List<UsersEntity> usersEntities = this.listWithTree(params);

        System.out.println(usersEntities);

        if(usersEntities == null) return urlMap;

        for(UsersEntity usersEntity: usersEntities){

            if(usersEntity.getRole() == null) continue;

            for(PowersEntity powersEntity:usersEntity.getRole().getPowers()) {
                String url = powersEntity.getPowerUrl();
                if(!urlMap.containsKey(url)){
                    urlMap.put(url, new CopyOnWriteArraySet<>());
                }
                String roleName = usersEntity.getRole().getRoleName();
                urlMap.get(url).add(roleName);
                urlMap = this.getUrlMap(urlMap,powersEntity.getChildren(),roleName);
            }
        }
        return urlMap;
    }
    public HashMap<String, CopyOnWriteArraySet<Object>> getUrlMap(HashMap<String, CopyOnWriteArraySet<Object>> urlMap, List<PowersEntity> children,String roleName) {
        for (PowersEntity powersEntity:children){

            String url = powersEntity.getPowerUrl();
            if(!urlMap.containsKey(url)){
                urlMap.put(url, new CopyOnWriteArraySet<>());
            }
            urlMap.get(url).add(roleName);
            urlMap = this.getUrlMap(urlMap,powersEntity.getChildren(),roleName);
        }
        return urlMap;
    }


}