package com.example.applemarketplace.port.rest.dto;

import lombok.Data;

@Data
public class GoodPurchaseDto {
    private String id;

    private GoodDto good;

    private Integer count;
}
