package com.gov.risk.indicator.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;

import com.gov.risk.indicator.dao.CollingDao;
import com.gov.risk.indicator.entity.CollingEntity;
import com.gov.risk.indicator.service.CollingService;


@Service("collingService")
public class CollingServiceImpl extends ServiceImpl<CollingDao, CollingEntity> implements CollingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollingEntity> page = this.page(
                new Query<CollingEntity>().getPage(params),
                new QueryWrapper<CollingEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CollingEntity> collingByIndexId(Map<String, Object> params) {
        List<CollingEntity> collingEntities = this.baseMapper.collingByIndexId(params);
        return collingEntities;
    }

}