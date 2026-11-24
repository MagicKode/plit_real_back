package com.plit.real.repository;

import com.plit.real.model.PortfolioCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioCategoryRepository extends JpaRepository<PortfolioCategory, Long> {
    List<PortfolioCategory> findAllByOrderByIdAsc();
}
