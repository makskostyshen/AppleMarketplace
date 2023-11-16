package com.example.applemarketplace.service.order;

import com.example.applemarketplace.service.model.Order;
import com.example.applemarketplace.service.model.PlaceOrderRequest;

public interface OrderService {
    Order placeOrder(PlaceOrderRequest placeOrderRequest);

    Order payOrder(String id);

    void deleteUnpaidOrders();
}
