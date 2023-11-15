package com.example.applemarketplace.port.rest.controller;

import com.example.applemarketplace.port.rest.RestPortMapper;
import com.example.applemarketplace.port.rest.dto.GoodDto;
import com.example.applemarketplace.port.rest.dto.GoodStockDto;
import com.example.applemarketplace.port.rest.dto.UpdateGoodStockRequestDto;
import com.example.applemarketplace.service.good.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @PostMapping
    public ResponseEntity<GoodDto> post(@RequestBody final GoodDto good) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        goodService.createGood(RestPortMapper.I.map(good))
                )
        );
    }


    @PatchMapping("/stock")
    public ResponseEntity<GoodStockDto> post(
            @RequestBody final UpdateGoodStockRequestDto requestDto) {
        return ResponseEntity.ok(
                RestPortMapper.I.map(
                        goodService.updateStock(RestPortMapper.I.map(requestDto))
                )
        );
    }
}
