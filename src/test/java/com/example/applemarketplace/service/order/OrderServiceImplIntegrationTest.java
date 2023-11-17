package com.example.applemarketplace.service.order;

import com.example.applemarketplace.data.order.OrderEntity;
import com.example.applemarketplace.data.order.OrderRepository;
import com.example.applemarketplace.exception.GoodNotFoundException;
import com.example.applemarketplace.exception.IllegalOrderPaymentException;
import com.example.applemarketplace.model.Order;
import com.example.applemarketplace.model.OrderStatus;
import com.example.applemarketplace.model.PlaceOrderPurchaseRequest;
import com.example.applemarketplace.model.PlaceOrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldNotPlaceOrderWhenGoodNotExists() {
        //given
        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .bill(BigDecimal.valueOf(11))
                .clientEmail("client@gmail.com")
                .purchases(List.of(
                        PlaceOrderPurchaseRequest.builder()
                                .goodId("non-existent")
                                .count(1)
                                .build()
                ))
                .build();

        //when, then
        assertThrows(GoodNotFoundException.class, ()->orderService.placeOrder(placeOrderRequest));
    }

    @Test
    void shouldPlaceOrderSuccessfully() {
        //given
        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .bill(BigDecimal.valueOf(11))
                .clientEmail("client@gmail.com")
                .purchases(List.of(
                        PlaceOrderPurchaseRequest.builder()
                                .goodId("good-id-1")
                                .count(2)
                                .build()
                ))
                .build();

        //when
        Order order = orderService.placeOrder(placeOrderRequest);

        // then
        assertEquals(order.getGoodPurchases().size(), 1);
        assertEquals(order.getGoodPurchases().get(0).getCount(), 2);
        assertEquals(order.getGoodPurchases().get(0).getGood().getId(), "good-id-1");
    }

    @Test
    @Transactional
    void shouldNotPayOrderWhenAlreadyPayed() {
        //given
        OrderEntity orderEntity = orderRepository.getById("order-id-1");
        orderEntity.setStatus(OrderStatus.PAID);
        orderRepository.save(orderEntity);

        //when, then
        assertThrows(
                IllegalOrderPaymentException.class,
                () -> orderService.payOrder("order-id-1", "client@gmail.com"));
    }

    @Test
    @Transactional
    void shouldNotPayOrderSuccessfully() {
        //given, when
        orderService.payOrder("order-id-1", "client@gmail.com");

        //then
        OrderEntity orderEntity = orderRepository.getById("order-id-1");
        assertEquals(orderEntity.getStatus(), OrderStatus.PAID);
        assertNotNull(orderEntity.getPayedOn());
    }
}