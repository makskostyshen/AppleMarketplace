package com.example.applemarketplace.service.good;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.GoodRepository;
import com.example.applemarketplace.data.good.stock.GoodStockEntity;
import com.example.applemarketplace.data.good.stock.GoodStockRepository;
import com.example.applemarketplace.exception.GoodNotFoundException;
import com.example.applemarketplace.service.ServiceLayerMapper;
import com.example.applemarketplace.service.model.Good;
import com.example.applemarketplace.service.model.GoodStock;
import com.example.applemarketplace.service.model.UpdateGoodStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;
    private final GoodStockRepository stockRepository;

    @Override
    public Good createGood(Good good) {
        GoodEntity goodEntity = goodRepository.save(ServiceLayerMapper.I.map(good));

        stockRepository.save(
                GoodStockEntity.builder()
                        .good(goodEntity)
                        .quantity(0L)
                        .build()
        );

        return ServiceLayerMapper.I.map(goodEntity);
    }

    @Override
    public GoodStock updateStock(UpdateGoodStock updateGoodStock) {
        return ServiceLayerMapper.I.map(
                goodRepository.findById(updateGoodStock.getGoodId())
                        .map(good -> {
                            GoodStockEntity stock = stockRepository.findByGood(good);
                            stock.updateQuantity(updateGoodStock.getQuantityChange());
                            stockRepository.save(stock);
                            return stock;
                        })
                        .orElseThrow(GoodNotFoundException::new)
        );
    }
}
