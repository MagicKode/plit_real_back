package com.plit.real.content.service.impl;

import com.plit.real.content.model.PriceEntity;
import com.plit.real.content.repository.PriceRepository;
import com.plit.real.content.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<PriceEntity> getAllPrices() {
        return priceRepository.findAllByOrderByIdAsc();
    }

    @Override
    public PriceEntity createPriceEntity(PriceEntity priceEntity) {
        priceEntity.setId(null);
        return priceRepository.save(priceEntity);
    }

    @Override
    public PriceEntity updatePriceEntity(Long id, PriceEntity priceEntity) {
        PriceEntity existing = priceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price not fount with id: " + id));
        existing.setServiceName(priceEntity.getServiceName());
        existing.setPrice(priceEntity.getPrice());
        existing.setCurrency(priceEntity.getCurrency());
        return priceRepository.save(existing);
    }

    @Override
    @Transactional
    public List<PriceEntity> updateAllPrices(List<PriceEntity> priceEntities) {
        // Находим ID строк, которые остались после редактирования в модалке
        Set<Long> incomingId = priceEntities.stream()
                .map(PriceEntity::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Удаляем из БД позиции, которые мастер удалил в UI
        List<PriceEntity> toDelete = priceRepository.findAll().stream()
                .filter(existing -> !incomingId.contains(existing.getId()))
                .toList();
        priceRepository.deleteAll(toDelete);

        return priceRepository.saveAll(priceEntities);
    }

    @Override
    public void deletePriceEntity(Long id) {
        priceRepository.deleteById(id);
    }
}
