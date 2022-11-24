package com.gov.risk.indicator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.indicator.entity.CollingEntity;

import java.util.List;
import java.util.Map;

/**
 * 指标依据表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
public interface CollingService extends IService<CollingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CollingEntity> collingByIndexId(Map<String, Object> params);


}

