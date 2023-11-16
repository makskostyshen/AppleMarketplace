package com.example.applemarketplace.port.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PlaceOrderRequestDto {
    private List<PlaceOrderPurchaseRequestDto> purchases;
    private BigDecimal bill;
}
