package com.gov.risk.auth.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.auth.sys.entity.RolesEntity;
import com.gov.risk.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
public interface RolesService extends IService<RolesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RolesEntity> listWithTree(Map<String, Object> params);
    String listWithTree2();
}

