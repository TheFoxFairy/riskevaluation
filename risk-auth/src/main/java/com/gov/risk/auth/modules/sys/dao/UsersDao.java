package com.gov.risk.auth.modules.sys.dao;

import com.gov.risk.auth.modules.sys.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Mapper
public interface UsersDao extends BaseMapper<UsersEntity> {
//    List<UsersEntity> listWithTree();
    List<UsersEntity> listWithTree(Map<String, Object> params);
}
