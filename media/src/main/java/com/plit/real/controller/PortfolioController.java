package com.plit.real.controller;

import com.plit.real.dto.CategoryWithWorksResponse;
import com.plit.real.model.PortfolioCategory;
import com.plit.real.service.PortfolioCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio/categories")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioCategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryWithWorksResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategoryWithWorks());
    }

    @PostMapping
    public ResponseEntity<PortfolioCategory> createCategory(@RequestBody PortfolioCategory category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
