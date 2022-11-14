package com.gov.risk.auth.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.auth.sys.entity.PowersEntity;
import com.gov.risk.common.utils.PageUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 权限表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
public interface PowersService extends IService<PowersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    HashMap<String, String> getPowerAllRoles();
}

