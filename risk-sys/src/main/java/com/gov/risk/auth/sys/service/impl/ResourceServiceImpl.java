package com.gov.risk.auth.sys.service.impl;

import com.gov.risk.auth.sys.constant.RedisConstant;
import com.gov.risk.auth.sys.service.ResourceService;
import com.gov.risk.auth.sys.service.UsersService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 资源与角色匹配关系管理业务类
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Resource
    UsersService usersService;

    public ResourceServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initData() {
        initPowerType("api",RedisConstant.RESOURCE_ROLES_MAP);
        initPowerType("页面",RedisConstant.RESOURCE_ROLES_PAGE_MAP);
    }

    private void initPowerType(String powerType,String resource_roles_map) {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("powerType",powerType); // 这里只放api
        HashMap<String, CopyOnWriteArraySet<Object>> map = usersService.listWithTree2(params);

        for (String key : map.keySet()) {
            List<String> resource = new ArrayList<>();
            for (Object value : map.get(key)) {
                resource.add(value.toString().toUpperCase());
            }
            resourceRolesMap.put(key, resource);
        }
        System.out.println(resourceRolesMap);
        redisTemplate.opsForHash().putAll(resource_roles_map, resourceRolesMap);
    }
}
