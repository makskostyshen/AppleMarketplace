package com.example.applemarketplace.service.model;

import lombok.Data;

@Data
public class UpdateGoodStock {
    private String goodId;
    private Long quantityChange;
}
