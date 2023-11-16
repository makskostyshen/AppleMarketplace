package com.example.applemarketplace.data.order;

import com.example.applemarketplace.service.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    void deleteByCreatedOnBeforeAndStatus(Instant createdOn, OrderStatus status);
}
