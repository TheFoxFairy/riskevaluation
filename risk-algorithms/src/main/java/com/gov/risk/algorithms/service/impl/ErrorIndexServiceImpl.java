package com.gov.risk.algorithms.service.impl;

import com.gov.risk.algorithms.dao.ErrorIndexDao;
import com.gov.risk.algorithms.entity.ErrorIndexEntity;
import com.gov.risk.algorithms.service.ErrorIndexService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;


@Service("errorIndexService")
public class ErrorIndexServiceImpl extends ServiceImpl<ErrorIndexDao, ErrorIndexEntity> implements ErrorIndexService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ErrorIndexEntity> page = this.page(
                new Query<ErrorIndexEntity>().getPage(params),
                new QueryWrapper<ErrorIndexEntity>()
        );

        return new PageUtils(page);
    }

}