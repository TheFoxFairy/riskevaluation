package com.gov.risk.indicator.dao;

import com.gov.risk.indicator.entity.IndexPropertyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 指标属性表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Mapper
public interface IndexPropertyDao extends BaseMapper<IndexPropertyEntity> {
    List<IndexPropertyEntity> indexPropertyByIndexId(Map<String, Object> params);

    List<String> countIdentification(Map<String, Object> params);
}
