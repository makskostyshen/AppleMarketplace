package com.example.applemarketplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    private List<PlaceOrderPurchaseRequest> purchases;
    private BigDecimal bill;
    private String clientEmail;
}
