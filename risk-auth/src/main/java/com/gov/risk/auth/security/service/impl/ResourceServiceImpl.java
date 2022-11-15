package com.gov.risk.auth.security.service.impl;

import com.gov.risk.auth.constant.RedisConstant;
import com.gov.risk.auth.security.service.ResourceService;
import com.gov.risk.auth.sys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 资源与角色匹配关系管理业务类
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UsersService usersService;

    public ResourceServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();

        HashMap<String, CopyOnWriteArraySet<Object>> map = usersService.listWithTree2(new HashMap<>());

        for (String key : map.keySet()) {
            List<String> resource = new ArrayList<>();
            for (Object value : map.get(key)) {
                resource.add(value.toString().toUpperCase());
            }
            resourceRolesMap.put(key, resource);
        }

        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
