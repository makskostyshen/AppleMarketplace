package com.example.applemarketplace.port.rest.dto;

import com.example.applemarketplace.model.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class OrderDto {
    private String id;
    private List<GoodPurchaseDto> goodPurchases;
    private OrderStatus status;
    private BigDecimal bill;
    private String clientId;
    private Instant createdOn;
    private Instant payedOn;
}
