package com.gov.risk.indicator.dao;

import com.gov.risk.indicator.entity.CollingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指标依据表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Mapper
public interface CollingDao extends BaseMapper<CollingEntity> {

    List<CollingEntity> collingByIndexId(Map<String, Object> params);
}
