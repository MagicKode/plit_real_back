package com.plit.real.service;

import com.plit.real.dto.CategoryWithWorksResponse;
import com.plit.real.model.PortfolioCategory;

import java.util.List;

public interface PortfolioCategoryService {
    List<CategoryWithWorksResponse> getCategoryWithWorks();
    PortfolioCategory createCategory(PortfolioCategory category);
    void deleteCategory(Long categoryId);
}
