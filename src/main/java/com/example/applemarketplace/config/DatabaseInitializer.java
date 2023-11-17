package com.example.applemarketplace.config;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.GoodRepository;
import com.example.applemarketplace.data.good.purchase.GoodPurchaseEntity;
import com.example.applemarketplace.data.good.stock.GoodStockEntity;
import com.example.applemarketplace.data.good.stock.GoodStockRepository;
import com.example.applemarketplace.data.order.OrderEntity;
import com.example.applemarketplace.data.order.OrderRepository;
import com.example.applemarketplace.data.user.UserAccountEntity;
import com.example.applemarketplace.data.user.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final GoodRepository goodRepository;
    private final OrderRepository orderRepository;
    private final GoodStockRepository goodStockRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public void run(String... args) {
        GoodEntity good = new GoodEntity();
        good.setName("iPhone 10 Max");
        good.setPrice(BigDecimal.valueOf(1000));

        GoodStockEntity goodStock = new GoodStockEntity();
        goodStock.setGood(good);
        goodStock.setQuantity(130L);

        GoodPurchaseEntity goodPurchase = new GoodPurchaseEntity();
        goodPurchase.setGood(good);
        goodPurchase.setCount(4);

        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setEmail("maxx");
        userAccountEntity.setPassword("pass");

        OrderEntity order = new OrderEntity(BigDecimal.valueOf(10000), List.of(goodPurchase), Instant.now());

        goodRepository.save(good);
        goodStockRepository.save(goodStock);
        orderRepository.save(order);
        userAccountRepository.save(userAccountEntity);
    }
}
