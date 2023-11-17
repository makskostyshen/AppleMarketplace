package com.example.applemarketplace.model;

import lombok.Data;

@Data
public class GoodPurchase {
    private String id;

    private Good good;

    private Integer count;
}
