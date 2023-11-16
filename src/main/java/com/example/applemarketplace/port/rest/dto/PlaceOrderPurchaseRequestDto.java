package com.example.applemarketplace.port.rest.dto;

import lombok.Data;

@Data
public class PlaceOrderPurchaseRequestDto {
    private String goodId;
    private Integer count;
}
