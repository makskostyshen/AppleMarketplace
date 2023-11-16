package com.example.applemarketplace.service.model;

import lombok.Data;

@Data
public class UpdateGoodStockRequest {
    private String goodId;
    private Long quantityChange;
}
