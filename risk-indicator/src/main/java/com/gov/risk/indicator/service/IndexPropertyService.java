package com.gov.risk.indicator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.indicator.entity.IndexPropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 指标属性表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
public interface IndexPropertyService extends IService<IndexPropertyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<IndexPropertyEntity> indexPropertyByIndexId(Map<String, Object> params);

    List<String> countIdentification(Map<String, Object> params);
}

