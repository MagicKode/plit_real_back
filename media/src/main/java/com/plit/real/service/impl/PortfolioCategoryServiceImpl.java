package com.plit.real.service.impl;

import com.plit.real.dto.CategoryWithWorksResponse;
import com.plit.real.dto.MediaResponse;
import com.plit.real.model.PortfolioCategory;
import com.plit.real.repository.PortfolioCategoryRepository;
import com.plit.real.service.MediaService;
import com.plit.real.service.PortfolioCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioCategoryServiceImpl implements PortfolioCategoryService {
    private final PortfolioCategoryRepository portfolioCategoryRepository;
    private final MediaService mediaService;

    @Override
    public List<CategoryWithWorksResponse> getCategoryWithWorks() {
        return portfolioCategoryRepository.findAllByOrderByIdAsc().stream()
                .map(category -> {
                    List<MediaResponse> works = mediaService.getFilesByCategory(category.getName());
                    return new CategoryWithWorksResponse(
                            category.getId(),
                            category.getName(),
                            category.getCoverImage(),
                            works
                    );
                })
                .toList();
    }

    @Override
    public PortfolioCategory createCategory(PortfolioCategory category) {
        category.setId(null);
        return portfolioCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        portfolioCategoryRepository.findById(categoryId).ifPresent(category -> {
            mediaService.deleteFilesByCategory(category.getName());
            mediaService.deletePhysicalFile(category.getCoverImage());
            portfolioCategoryRepository.delete(category);
        });
    }
}
