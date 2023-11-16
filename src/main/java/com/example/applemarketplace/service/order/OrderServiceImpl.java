package com.example.applemarketplace.service.order;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.GoodRepository;
import com.example.applemarketplace.data.good.purchase.GoodPurchaseEntity;
import com.example.applemarketplace.data.order.OrderEntity;
import com.example.applemarketplace.data.order.OrderRepository;
import com.example.applemarketplace.service.ServiceLayerMapper;
import com.example.applemarketplace.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final GoodRepository goodRepository;

    @Override
    public Order placeOrder(final PlaceOrderRequest placeOrderRequest) {
        Instant createdOn = Instant.now();

        Map<String, Integer> goodIdCountMap = placeOrderRequest.getPurchases()
                .stream()
                .collect(Collectors.toMap(PlaceOrderPurchaseRequest::getGoodId, PlaceOrderPurchaseRequest::getCount));

        List<GoodEntity> goodEntities = goodRepository.findAllById(
                placeOrderRequest.getPurchases().stream()
                        .map(PlaceOrderPurchaseRequest::getGoodId)
                        .collect(Collectors.toList())
                );

        List<GoodPurchaseEntity> goodPurchases = goodEntities.stream()
                .map(good -> GoodPurchaseEntity.builder()
                        .good(good)
                        .count(goodIdCountMap.get(good.getId()))
                        .build()
                ).collect(Collectors.toList());

        return ServiceLayerMapper.I.map(orderRepository.save(
                new OrderEntity(placeOrderRequest.getBill(), goodPurchases, createdOn)
        ));
    }

    @Override
    public Order payOrder(final String id) {
        OrderEntity order = orderRepository.getById(id);
        order.setStatus(OrderStatus.PAID);
        order.setPayedOn(Instant.now());
        return ServiceLayerMapper.I.map(order);
    }
}
