package com.gov.risk.auth.sys.dao;

import com.gov.risk.auth.sys.entity.RolesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Mapper
public interface RolesDao extends BaseMapper<RolesEntity> {

    List<RolesEntity> listWithTree(Map<String, Object> params);
}
