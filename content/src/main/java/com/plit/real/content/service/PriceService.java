package com.plit.real.content.service;

import com.plit.real.content.model.PriceEntity;

import java.util.List;

public interface PriceService {
    List<PriceEntity> getAllPrices();
    PriceEntity createPriceEntity(PriceEntity priceEntity);
    PriceEntity updatePriceEntity(Long id, PriceEntity priceEntity);
    List<PriceEntity> updateAllPrices(List<PriceEntity> priceEntities);
    void deletePriceEntity(Long id);
}
