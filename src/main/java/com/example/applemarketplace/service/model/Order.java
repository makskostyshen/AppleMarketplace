package com.example.applemarketplace.service.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class Order {
    private String id;
    private List<GoodPurchase> goodPurchases;
    private OrderStatus status;
    private BigDecimal bill;
    private ClientProfile client;
    private Instant createdOn;
    private Instant payedOn;
}
