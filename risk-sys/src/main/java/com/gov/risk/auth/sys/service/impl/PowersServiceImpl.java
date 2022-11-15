package com.gov.risk.auth.sys.service.impl;

import com.gov.risk.auth.sys.dao.PowersDao;
import com.gov.risk.auth.sys.entity.PowersEntity;
import com.gov.risk.auth.sys.service.PowersService;
import com.gov.risk.auth.sys.service.UsersService;
import com.gov.risk.common.utils.R;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;


@Service("powersService")
public class PowersServiceImpl extends ServiceImpl<PowersDao, PowersEntity> implements PowersService {

    @Autowired
    UsersService usersService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PowersEntity> page = this.page(
                new Query<PowersEntity>().getPage(params),
                new QueryWrapper<PowersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public HashMap<String, String> getPowerAllRoles() {

        Map<String, Object> params = new HashMap<>();

        HashMap<String, CopyOnWriteArraySet<Object>> map = usersService.listWithTree2(params);
        HashMap<String, String> urlMap = new HashMap<>();

        for (String key:map.keySet()){
            for(Object value:map.get(key)){
                urlMap.put(key,value.toString());
            }
        }

        return urlMap;
    }

}