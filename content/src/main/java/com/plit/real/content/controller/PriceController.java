package com.plit.real.content.controller;

import com.plit.real.content.model.PriceEntity;
import com.plit.real.content.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<List<PriceEntity>> getAllPrices() {
        return ResponseEntity.ok(priceService.getAllPrices());
    }

    @PostMapping
    public ResponseEntity<PriceEntity> create(@RequestBody PriceEntity price) {
        return ResponseEntity.ok(priceService.createPriceEntity(price));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceEntity> update(@PathVariable Long id, @RequestBody PriceEntity price) {
        return ResponseEntity.ok(priceService.updatePriceEntity(id, price));
    }

    @PutMapping
    public ResponseEntity<List<PriceEntity>> updateAll(@RequestBody List<PriceEntity> prices) {
        return ResponseEntity.ok(priceService.updateAllPrices(prices));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        priceService.deletePriceEntity(id);
        return ResponseEntity.ok().build();
    }
}
