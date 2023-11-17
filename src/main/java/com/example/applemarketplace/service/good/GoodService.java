package com.example.applemarketplace.service.good;

import com.example.applemarketplace.model.Good;
import com.example.applemarketplace.model.GoodStock;
import com.example.applemarketplace.model.UpdateGoodStockRequest;

import java.util.List;

public interface GoodService {
    Good createGood(Good good);

    GoodStock updateStock(UpdateGoodStockRequest updateGoodStockRequest);

    List<GoodStock> getAllGoodStocks();
}
