package com.example.applemarketplace.port.rest.controller;

import com.example.applemarketplace.port.rest.RestPortMapper;
import com.example.applemarketplace.port.rest.dto.OrderDto;
import com.example.applemarketplace.service.model.PlaceOrderRequest;
import com.example.applemarketplace.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody final PlaceOrderRequest placeOrderRequest) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        orderService.placeOrder(placeOrderRequest)
                )
        );
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<OrderDto> payOrder(@PathVariable final String id) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        orderService.payOrder(id)
                )
        );
    }
}
