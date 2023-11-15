package com.example.applemarketplace.service;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.stock.GoodStockEntity;
import com.example.applemarketplace.service.model.Good;
import com.example.applemarketplace.service.model.GoodStock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceLayerMapper {
    ServiceLayerMapper I = Mappers.getMapper(ServiceLayerMapper.class);

    GoodEntity map(Good good);

    Good map(GoodEntity goodEntity);

    GoodStock map(GoodStockEntity stockEntity);
}
