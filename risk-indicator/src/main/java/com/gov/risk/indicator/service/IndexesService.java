package com.gov.risk.indicator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.indicator.entity.IndexesEntity;

import java.util.List;
import java.util.Map;

/**
 * 指标表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
public interface IndexesService extends IService<IndexesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<List<IndexesEntity>> listWithTree(Map<String, Object> params);
}

