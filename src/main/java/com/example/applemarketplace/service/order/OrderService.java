package com.example.applemarketplace.service.order;

import com.example.applemarketplace.model.Order;
import com.example.applemarketplace.model.PlaceOrderRequest;

public interface OrderService {
    Order placeOrder(PlaceOrderRequest placeOrderRequest);

    Order payOrder(String id, String email);

    void deleteUnpaidOrders();
}
