package com.example.applemarketplace.port.rest;

import com.example.applemarketplace.port.rest.dto.*;
import com.example.applemarketplace.service.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RestPortMapper {
    RestPortMapper I = Mappers.getMapper(RestPortMapper.class);

    Good map(GoodDto goodDto);

    GoodDto map(Good good);

    UpdateGoodStockRequest map(UpdateGoodStockRequestDto requestDto);

    GoodStockDto map(GoodStock goodStock);

    GoodPurchaseDto map(GoodPurchase goodPurchase);

    @Mapping(target = "clientId", source = "client.id")
    OrderDto map(Order order);

    PlaceOrderPurchaseRequest map(PlaceOrderPurchaseRequestDto placeOrderPurchaseRequestDto);
}
