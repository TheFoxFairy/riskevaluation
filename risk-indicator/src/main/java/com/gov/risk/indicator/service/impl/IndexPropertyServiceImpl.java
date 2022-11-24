package com.gov.risk.indicator.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;

import com.gov.risk.indicator.dao.IndexPropertyDao;
import com.gov.risk.indicator.entity.IndexPropertyEntity;
import com.gov.risk.indicator.service.IndexPropertyService;


@Service("indexPropertyService")
public class IndexPropertyServiceImpl extends ServiceImpl<IndexPropertyDao, IndexPropertyEntity> implements IndexPropertyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IndexPropertyEntity> page = this.page(
                new Query<IndexPropertyEntity>().getPage(params),
                new QueryWrapper<IndexPropertyEntity>()
        );

        return new PageUtils(page);
    }

    public List<IndexPropertyEntity> indexPropertyByIndexId(Map<String, Object> params){
        return this.baseMapper.indexPropertyByIndexId(params);
    }

    @Override
    public List<String> countIdentification(Map<String, Object> params) {
        return this.baseMapper.countIdentification(params);
    }

}