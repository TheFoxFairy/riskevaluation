package com.gov.risk.auth.modules.sys.dao;

import com.gov.risk.auth.modules.sys.entity.UserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色（关系）表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {
	
}
