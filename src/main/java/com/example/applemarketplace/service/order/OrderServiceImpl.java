package com.example.applemarketplace.service.order;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.GoodRepository;
import com.example.applemarketplace.data.good.purchase.GoodPurchaseEntity;
import com.example.applemarketplace.data.order.OrderEntity;
import com.example.applemarketplace.data.order.OrderRepository;
import com.example.applemarketplace.data.user.ClientProfileEntity;
import com.example.applemarketplace.data.user.UserAccountEntity;
import com.example.applemarketplace.data.user.UserAccountRepository;
import com.example.applemarketplace.exception.GoodNotFoundException;
import com.example.applemarketplace.exception.IllegalOrderPaymentException;
import com.example.applemarketplace.exception.OrderNotFoundException;
import com.example.applemarketplace.exception.UserNotFoundException;
import com.example.applemarketplace.model.Order;
import com.example.applemarketplace.model.OrderStatus;
import com.example.applemarketplace.model.PlaceOrderPurchaseRequest;
import com.example.applemarketplace.model.PlaceOrderRequest;
import com.example.applemarketplace.service.ServiceLayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final Integer UNPAID_ORDER_MAX_VALID_DURATION = 10 * 60;

    private final UserAccountRepository userAccountRepository;
    private final OrderRepository orderRepository;
    private final GoodRepository goodRepository;

    @Override
    public Order placeOrder(final PlaceOrderRequest placeOrderRequest) {
        Instant createdOn = Instant.now();

        ClientProfileEntity clientProfileEntity =
                userAccountRepository.findByEmail(placeOrderRequest.getClientEmail())
                        .map(UserAccountEntity::getClientProfile)
                        .orElseThrow(UserNotFoundException::new);

        List<GoodPurchaseEntity> goodPurchases = getPurchaseEntities(placeOrderRequest.getPurchases());

        if (goodPurchases.size() != placeOrderRequest.getPurchases().size()) {
            throw new GoodNotFoundException();
        }

        return ServiceLayerMapper.I.map(orderRepository.save(
                new OrderEntity(placeOrderRequest.getBill(), goodPurchases, clientProfileEntity, createdOn)
        ));
    }

    @Override
    public Order payOrder(final String id, final String email) {
        ClientProfileEntity clientProfileEntity =
                userAccountRepository.findByEmail(email)
                        .map(UserAccountEntity::getClientProfile)
                        .orElseThrow(UserNotFoundException::new);

        OrderEntity order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() == OrderStatus.PAID) {
            throw new IllegalOrderPaymentException();
        }
        if (!clientProfileEntity.equals(order.getClientProfile())) {
            throw new IllegalOrderPaymentException();
        }

        order.setStatus(OrderStatus.PAID);
        order.setPayedOn(Instant.now());
        return ServiceLayerMapper.I.map(order);
    }

    @Override
    public void deleteUnpaidOrders() {
        Instant maxValidCreationTime = Instant.now().minus(Duration.ofMinutes(UNPAID_ORDER_MAX_VALID_DURATION));
        orderRepository.deleteByCreatedOnBeforeAndStatus(maxValidCreationTime, OrderStatus.UNPAID);
    }

    private List<GoodPurchaseEntity> getPurchaseEntities(final List<PlaceOrderPurchaseRequest> purchaseRequests) {
        Map<String, Integer> goodIdCountMap = purchaseRequests
                .stream()
                .collect(Collectors.toMap(PlaceOrderPurchaseRequest::getGoodId, PlaceOrderPurchaseRequest::getCount));

        List<GoodEntity> goodEntities = goodRepository.findAllById(
                purchaseRequests.stream()
                        .map(PlaceOrderPurchaseRequest::getGoodId)
                        .collect(Collectors.toList())
        );

        return goodEntities.stream()
                .map(good -> GoodPurchaseEntity.builder()
                        .good(good)
                        .count(goodIdCountMap.get(good.getId()))
                        .build()
                ).collect(Collectors.toList());
    }
}
