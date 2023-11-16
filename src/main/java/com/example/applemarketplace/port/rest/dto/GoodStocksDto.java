package com.example.applemarketplace.port.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodStocksDto {
    private List<GoodStockDto> goodStocks;
}
