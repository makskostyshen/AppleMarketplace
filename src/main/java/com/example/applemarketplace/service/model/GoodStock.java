package com.example.applemarketplace.service.model;

import lombok.Data;

@Data
public class GoodStock {
    private String id;
    private Good good;
    private Long quantity;
}
