package com.gov.risk.auth.sys.service.impl;

import com.gov.risk.auth.sys.dao.RolesDao;
import com.gov.risk.auth.sys.entity.PowersEntity;
import com.gov.risk.auth.sys.entity.RolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;

import com.gov.risk.auth.sys.service.RolesService;


@Service("rolesService")
public class RolesServiceImpl extends ServiceImpl<RolesDao, RolesEntity> implements RolesService {

    @Autowired
    RolesDao rolesDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RolesEntity> page = this.page(
                new Query<RolesEntity>().getPage(params),
                new QueryWrapper<RolesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<RolesEntity> listWithTree(Map<String, Object> params) {
        List<RolesEntity> rolesEntities = this.baseMapper.listWithTree(params);

        // 组装powers
        for (int i = 0; i < rolesEntities.size(); i++) {
            RolesEntity rolesEntity = rolesEntities.get(i);

            List<PowersEntity> powers = rolesEntity.getPowers();

            // 重构powers，按照分类结构显示
            powers = reorganizePowers(powers);

            rolesEntity.setPowers(powers);
            rolesEntities.set(i,rolesEntity);
        }

        return rolesEntities;
    }

    @Override
    public String listWithTree2() {

        Map<String, Object> params = new HashMap<>();
        List<RolesEntity> rolesEntities = this.baseMapper.listWithTree(params);

        StringBuilder s = new StringBuilder();

        // 组装powers
        for (int i = 0; i < rolesEntities.size(); i++) {

            if(i == 0)
                s.append(rolesEntities.get(i).getRoleName());
            else {
                s.append(",");
                s.append(rolesEntities.get(i).getRoleName());
            }

        }

        return s.toString();
    }

    private List<PowersEntity> reorganizePowers(List<PowersEntity> powersEntities) {

        List<PowersEntity> newPowersEntities = powersEntities.stream().filter(powersEntity -> {
            return powersEntity.getPowerParentId() == -1;
        }).map((power) -> {
            power.setChildren(getPowerChildren(power, powersEntities,power.getPowerUrl()));
            return power;
        }).collect(Collectors.toList());

        return newPowersEntities;

    }

    private List<PowersEntity> getPowerChildren(PowersEntity root, List<PowersEntity> powersEntities,String powerUrl) {
        List<PowersEntity> children = powersEntities.stream().filter(powersEntity -> {
            return powersEntity.getPowerParentId() == root.getPowerId();
        }).map(powersEntity -> {
            String url = powerUrl+powersEntity.getPowerUrl();
            powersEntity.setPowerUrl(url);
            powersEntity.setChildren(getPowerChildren(powersEntity, powersEntities,url));
            return powersEntity;
        }).collect(Collectors.toList());
        return children;
    }

}