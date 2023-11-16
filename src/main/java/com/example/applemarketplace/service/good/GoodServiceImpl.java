package com.example.applemarketplace.service.good;

import com.example.applemarketplace.data.good.GoodEntity;
import com.example.applemarketplace.data.good.GoodRepository;
import com.example.applemarketplace.data.good.stock.GoodStockEntity;
import com.example.applemarketplace.data.good.stock.GoodStockRepository;
import com.example.applemarketplace.exception.GoodNotFoundException;
import com.example.applemarketplace.service.ServiceLayerMapper;
import com.example.applemarketplace.service.model.Good;
import com.example.applemarketplace.service.model.GoodStock;
import com.example.applemarketplace.service.model.UpdateGoodStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;
    private final GoodStockRepository stockRepository;

    @Override
    public Good createGood(final Good good) {
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
    public GoodStock updateStock(final UpdateGoodStockRequest updateGoodStockRequest) {
        return ServiceLayerMapper.I.map(
                goodRepository.findById(updateGoodStockRequest.getGoodId())
                        .map(good -> {
                            GoodStockEntity stock = stockRepository.findByGood(good);
                            stock.updateQuantity(updateGoodStockRequest.getQuantityChange());
                            stockRepository.save(stock);
                            return stock;
                        })
                        .orElseThrow(GoodNotFoundException::new)
        );
    }

    @Override
    public List<GoodStock> getAllGoodStocks() {
        return stockRepository.findAll().stream()
                .map(ServiceLayerMapper.I::map)
                .collect(Collectors.toList());
    }
}
