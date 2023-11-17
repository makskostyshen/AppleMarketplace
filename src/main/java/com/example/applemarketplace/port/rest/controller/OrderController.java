package com.example.applemarketplace.port.rest.controller;

import com.example.applemarketplace.port.rest.RestPortMapper;
import com.example.applemarketplace.port.rest.dto.OrderDto;
import com.example.applemarketplace.port.rest.dto.PlaceOrderRequestDto;
import com.example.applemarketplace.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<OrderDto> placeOrder(
            @RequestBody final PlaceOrderRequestDto placeOrderRequest,
            final Principal principal) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        orderService.placeOrder(
                                RestPortMapper.I.map(placeOrderRequest, principal)
                        )
                )
        );
    }

    @PatchMapping("/{id}/pay")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<OrderDto> payOrder(
            @PathVariable final String id,
            final Principal principal) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        orderService.payOrder(id, principal.getName())
                )
        );
    }
}
