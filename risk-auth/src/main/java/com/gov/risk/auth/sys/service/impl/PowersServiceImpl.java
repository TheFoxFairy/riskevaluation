package com.gov.risk.auth.sys.service.impl;

import com.gov.risk.auth.sys.dao.PowersDao;
import com.gov.risk.auth.sys.entity.PowersEntity;
import com.gov.risk.auth.sys.service.PowersService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;


@Service("powersService")
public class PowersServiceImpl extends ServiceImpl<PowersDao, PowersEntity> implements PowersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PowersEntity> page = this.page(
                new Query<PowersEntity>().getPage(params),
                new QueryWrapper<PowersEntity>()
        );

        return new PageUtils(page);
    }

}