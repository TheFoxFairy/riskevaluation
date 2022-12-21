package com.gov.risk.algorithms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.algorithms.entity.ErrorIndexEntity;
import com.gov.risk.common.utils.PageUtils;

import java.util.Map;

/**
 * 指标依据与指标表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-12-21 19:20:01
 */
public interface ErrorIndexService extends IService<ErrorIndexEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

