package com.example.applemarketplace.port.rest;

import com.example.applemarketplace.model.*;
import com.example.applemarketplace.port.rest.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.security.Principal;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RestPortMapper {
    RestPortMapper I = Mappers.getMapper(RestPortMapper.class);

    Good map(GoodDto goodDto);

    GoodDto map(Good good);

    UpdateGoodStockRequest map(UpdateGoodStockRequestDto requestDto);

    GoodStockDto map(GoodStock goodStock);

    GoodPurchaseDto map(GoodPurchase goodPurchase);

    @Mapping(target = "clientId", source = "clientProfile.id")
    OrderDto map(Order order);

    PlaceOrderPurchaseRequest map(PlaceOrderPurchaseRequestDto placeOrderPurchaseRequestDto);

    @Mapping(target = "bill", source = "placeOrderRequestDto.bill")
    @Mapping(target = "purchases", source = "placeOrderRequestDto.purchases")
    @Mapping(target = "clientEmail", source = "principal.name")
    PlaceOrderRequest map(PlaceOrderRequestDto placeOrderRequestDto, Principal principal);
}
