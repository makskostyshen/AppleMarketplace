package com.example.applemarketplace.port.rest.controller;

import com.example.applemarketplace.port.rest.RestPortMapper;
import com.example.applemarketplace.port.rest.dto.GoodDto;
import com.example.applemarketplace.port.rest.dto.GoodStockDto;
import com.example.applemarketplace.port.rest.dto.GoodStocksDto;
import com.example.applemarketplace.port.rest.dto.UpdateGoodStockRequestDto;
import com.example.applemarketplace.service.good.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @PostMapping
    public ResponseEntity<GoodDto> createGood(@RequestBody final GoodDto good) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        goodService.createGood(RestPortMapper.I.map(good))
                )
        );
    }


    @PatchMapping("/stocks")
    public ResponseEntity<GoodStockDto> updateGoodStock(
            @RequestBody final UpdateGoodStockRequestDto requestDto) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        goodService.updateStock(RestPortMapper.I.map(requestDto))
                )
        );
    }

    @GetMapping ("/stocks")
    public ResponseEntity<GoodStocksDto> getAllGoodStocks() {
        return ResponseEntity.ok(
                new GoodStocksDto(
                        goodService.getAllGoodStocks()
                                .stream()
                                .map(RestPortMapper.I::map)
                                .collect(Collectors.toList())
                )
        );
    }
}
