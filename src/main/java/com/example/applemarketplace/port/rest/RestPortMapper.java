package com.example.applemarketplace.port.rest;

import com.example.applemarketplace.port.rest.dto.GoodDto;
import com.example.applemarketplace.port.rest.dto.GoodStockDto;
import com.example.applemarketplace.port.rest.dto.UpdateGoodStockRequestDto;
import com.example.applemarketplace.service.model.Good;
import com.example.applemarketplace.service.model.GoodStock;
import com.example.applemarketplace.service.model.UpdateGoodStock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestPortMapper {
    RestPortMapper I = Mappers.getMapper(RestPortMapper.class);

    Good map(GoodDto goodDto);

    GoodDto map(Good good);

    UpdateGoodStock map(UpdateGoodStockRequestDto requestDto);

    GoodStockDto map(GoodStock goodStock);
}
