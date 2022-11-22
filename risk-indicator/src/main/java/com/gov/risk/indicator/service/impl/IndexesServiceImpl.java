package com.gov.risk.indicator.service.impl;

import com.gov.risk.indicator.entity.IndexPropertyEntity;
import com.gov.risk.indicator.service.CollingService;
import com.gov.risk.indicator.service.IndexPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gov.risk.common.utils.PageUtils;
import com.gov.risk.common.utils.Query;

import com.gov.risk.indicator.dao.IndexesDao;
import com.gov.risk.indicator.entity.IndexesEntity;
import com.gov.risk.indicator.service.IndexesService;


@Service("indexesService")
public class IndexesServiceImpl extends ServiceImpl<IndexesDao, IndexesEntity> implements IndexesService {

    @Autowired
    CollingService collingService;

    @Autowired
    IndexPropertyService indexPropertyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IndexesEntity> page = this.page(
                new Query<IndexesEntity>().getPage(params),
                new QueryWrapper<IndexesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<List<IndexesEntity>> listWithTree(Map<String, Object> params) { // 为了方便禁止一下子获取所有表

        Object identification = params.get("identification");
        params.remove("identification");

        List<IndexesEntity> indexesEntities = this.baseMapper.selectByMap(params);
        List<List<IndexesEntity>> indexesEntitiesUniqueIdentification = new ArrayList<>();

        HashMap<String,Object> propertiesParams = new HashMap<>();
        propertiesParams.put("identification",identification);
        List<String> identifications = this.indexPropertyService.countIdentification(propertiesParams);

        for (String iden:identifications) {
//            indexesEntities = this.indexesEntitiesAddOther(indexesEntities, iden);
            indexesEntitiesUniqueIdentification.add(this.indexesEntitiesAddOther(indexesEntities, iden));
        }

        return indexesEntitiesUniqueIdentification;
    }

    private List<IndexesEntity> indexesEntitiesAddOther(List<IndexesEntity> indexesEntities,String identification) {

        List<IndexesEntity> newIndexesEntities = indexesEntities.stream().filter(
                indexesEntity -> indexesEntity.getIndexParentId() == -1
        ).map((indexesEntity) -> {
            indexesEntity.setChildren(getIndexesEntityChildren(indexesEntity, indexesEntities,identification));
            return indexesEntity;
        }).collect(Collectors.toList());

        return newIndexesEntities;
    }

    private List<IndexesEntity> getIndexesEntityChildren(IndexesEntity root, List<IndexesEntity> indexesEntities,String identification) {

        List<IndexesEntity> children = indexesEntities.stream().filter(indexesEntity -> {
            return Objects.equals(indexesEntity.getIndexParentId(), root.getIndexId());
        }).map(indexesEntity -> {

            if(Objects.equals(indexesEntity.getIndexType(), "观测指标")){
                HashMap<String,Object> collingsParams = new HashMap<>();
                collingsParams.put("indexId",indexesEntity.getIndexId());
                indexesEntity.setCollingEntities(this.collingService.collingByIndexId(collingsParams));

                HashMap<String,Object> propertiesParams = new HashMap<>();
                propertiesParams.put("indexId",indexesEntity.getIndexId());
                propertiesParams.put("identification",identification);
                List<IndexPropertyEntity> indexPropertyEntities = this.indexPropertyService.indexPropertyByIndexId(propertiesParams);
                IndexPropertyEntity indexPropertyEntity = indexPropertyEntities.size() == 0?null:indexPropertyEntities.get(0);
                indexesEntity.setIndexPropertyEntity(indexPropertyEntity);
            }

            indexesEntity.setChildren(getIndexesEntityChildren(indexesEntity, indexesEntities,identification));
            return indexesEntity;
        }).collect(Collectors.toList());
        return children;
    }


}