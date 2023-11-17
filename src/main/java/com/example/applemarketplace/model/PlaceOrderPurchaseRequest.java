package com.example.applemarketplace.model;

import lombok.Data;

@Data
public class PlaceOrderPurchaseRequest {
    private String goodId;
    private Integer count;
}
