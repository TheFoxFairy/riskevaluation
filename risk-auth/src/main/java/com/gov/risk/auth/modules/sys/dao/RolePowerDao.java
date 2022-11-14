package com.gov.risk.auth.modules.sys.dao;

import com.gov.risk.auth.modules.sys.entity.RolePowerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限（关系）表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Mapper
public interface RolePowerDao extends BaseMapper<RolePowerEntity> {
	
}
