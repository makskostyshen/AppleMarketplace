package com.example.applemarketplace.service;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.purchase.GoodPurchaseEntity;
import com.example.applemarketplace.data.good.stock.GoodStockEntity;
import com.example.applemarketplace.data.order.OrderEntity;
import com.example.applemarketplace.data.user.ClientProfileEntity;
import com.example.applemarketplace.data.user.UserAccountEntity;
import com.example.applemarketplace.service.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ServiceLayerMapper {
    ServiceLayerMapper I = Mappers.getMapper(ServiceLayerMapper.class);

    GoodEntity map(Good good);

    Good map(GoodEntity goodEntity);

    GoodStock map(GoodStockEntity stockEntity);

    GoodPurchase map(GoodPurchaseEntity goodPurchaseEntity);

    ClientProfile map(ClientProfileEntity clientProfileEntity);

    UserAccount map(UserAccountEntity userAccountEntity);

    Order map(OrderEntity orderEntity);
}
