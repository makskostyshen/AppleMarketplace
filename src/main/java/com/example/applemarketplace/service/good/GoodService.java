package com.example.applemarketplace.service.good;

import com.example.applemarketplace.service.model.Good;
import com.example.applemarketplace.service.model.GoodStock;
import com.example.applemarketplace.service.model.UpdateGoodStockRequest;

import java.util.List;

public interface GoodService {
    Good createGood(Good good);

    GoodStock updateStock(UpdateGoodStockRequest updateGoodStockRequest);

    List<GoodStock> getAllGoodStocks();
}
