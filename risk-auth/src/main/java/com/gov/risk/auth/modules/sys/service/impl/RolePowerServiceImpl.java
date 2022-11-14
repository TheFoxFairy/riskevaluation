package com.gov.risk.auth.modules.sys.service.impl;

import com.gov.risk.auth.modules.sys.dao.RolePowerDao;
import com.gov.risk.auth.modules.sys.entity.RolePowerEntity;
import com.gov.risk.auth.modules.sys.service.RolePowerService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;


@Service("rolePowerService")
public class RolePowerServiceImpl extends ServiceImpl<RolePowerDao, RolePowerEntity> implements RolePowerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RolePowerEntity> page = this.page(
                new Query<RolePowerEntity>().getPage(params),
                new QueryWrapper<RolePowerEntity>()
        );

        return new PageUtils(page);
    }

}