package com.example.applemarketplace.service.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Good {
    private String id;
    private String name;
    private BigDecimal price;
}
