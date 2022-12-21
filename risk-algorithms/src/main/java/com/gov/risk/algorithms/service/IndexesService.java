package com.gov.risk.algorithms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gov.risk.algorithms.entity.IndexesEntity;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 指标表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Service
@FeignClient(name = "risk-indicator")
public interface IndexesService{
    @RequestMapping(value = "/indicator/indexes/tree",method = RequestMethod.GET)
    R listWithTree(Map<String, Object> params);
}

