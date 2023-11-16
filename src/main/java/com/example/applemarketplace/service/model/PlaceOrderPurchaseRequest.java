package com.example.applemarketplace.service.model;

import lombok.Data;

@Data
public class PlaceOrderPurchaseRequest {
    private String goodId;
    private Integer count;
}
