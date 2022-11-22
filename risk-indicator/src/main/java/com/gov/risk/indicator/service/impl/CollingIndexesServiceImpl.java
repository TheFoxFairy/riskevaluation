package com.gov.risk.indicator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;

import com.gov.risk.indicator.dao.CollingIndexesDao;
import com.gov.risk.indicator.entity.CollingIndexesEntity;
import com.gov.risk.indicator.service.CollingIndexesService;


@Service("collingIndexesService")
public class CollingIndexesServiceImpl extends ServiceImpl<CollingIndexesDao, CollingIndexesEntity> implements CollingIndexesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollingIndexesEntity> page = this.page(
                new Query<CollingIndexesEntity>().getPage(params),
                new QueryWrapper<CollingIndexesEntity>()
        );

        return new PageUtils(page);
    }

}