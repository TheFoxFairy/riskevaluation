package com.gov.risk.indicator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.indicator.entity.CollingIndexesEntity;

import java.util.Map;

/**
 * 指标依据与指标表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
public interface CollingIndexesService extends IService<CollingIndexesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

