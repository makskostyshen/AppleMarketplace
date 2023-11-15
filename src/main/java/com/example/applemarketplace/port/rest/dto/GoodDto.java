package com.example.applemarketplace.port.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodDto {
    private String id;
    private String name;
    private BigDecimal price;
}
