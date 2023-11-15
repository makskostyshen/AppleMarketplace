package com.example.applemarketplace.data.good.stock;

import com.example.applemarketplace.data.good.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodStockRepository extends JpaRepository<GoodStockEntity, String> {
    GoodStockEntity findByGood(GoodEntity good);
}
