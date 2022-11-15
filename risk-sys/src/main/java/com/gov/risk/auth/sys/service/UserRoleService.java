package com.gov.risk.auth.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.auth.sys.entity.UserRoleEntity;
import com.gov.risk.common.utils.PageUtils;

import java.util.Map;

/**
 * 用户角色（关系）表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

