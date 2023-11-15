package com.example.applemarketplace.port.rest.dto;

import lombok.Data;

@Data
public class GoodStockDto {
    private String id;
    private GoodDto good;
    private Long quantity;
}
