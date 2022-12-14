package com.gov.risk.auth.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.auth.sys.entity.UsersEntity;
import com.gov.risk.common.utils.PageUtils;
import org.springframework.cloud.openfeign.FeignClient;

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
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UsersEntity> listWithTree(Map<String, Object> params);

    HashMap<String, CopyOnWriteArraySet<Object>> listWithTree2(Map<String, Object> params);
//    HashMap<String, CopyOnWriteArraySet<Object>> listWithTreeByPowerType(Map<String, Object> params);
}

