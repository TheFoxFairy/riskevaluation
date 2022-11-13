package com.gov.risk.auth.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.auth.modules.sys.entity.RolePowerEntity;
import com.gov.risk.common.utils.PageUtils;

import java.util.Map;

/**
 * 角色权限（关系）表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
public interface RolePowerService extends IService<RolePowerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

