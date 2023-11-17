package com.example.applemarketplace.model;

import lombok.Data;

@Data
public class UpdateGoodStockRequest {
    private String goodId;
    private Long quantityChange;
}
