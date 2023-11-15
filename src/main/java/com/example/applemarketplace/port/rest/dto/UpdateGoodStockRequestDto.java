package com.example.applemarketplace.port.rest.dto;

import lombok.Data;

@Data
public class UpdateGoodStockRequestDto {
    private String goodId;
    private Long quantityChange;
}
