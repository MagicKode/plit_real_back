package com.plit.real.content.repository;

import com.plit.real.content.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findAllByOrderByIdAsc();
}
