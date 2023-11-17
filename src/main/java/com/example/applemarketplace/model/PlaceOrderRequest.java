package com.example.applemarketplace.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PlaceOrderRequest {
    private List<PlaceOrderPurchaseRequest> purchases;
    private BigDecimal bill;
    private String clientEmail;
}
