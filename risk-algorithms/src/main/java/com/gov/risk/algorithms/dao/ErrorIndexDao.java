package com.gov.risk.algorithms.dao;

import com.gov.risk.algorithms.entity.ErrorIndexEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标依据与指标表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-12-21 19:20:01
 */
@Mapper
public interface ErrorIndexDao extends BaseMapper<ErrorIndexEntity> {
	
}
