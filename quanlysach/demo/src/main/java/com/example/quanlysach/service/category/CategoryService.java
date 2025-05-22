package com.example.quanlysach.service.category;

import com.example.quanlysach.dto.category.CategoryDTO;
import com.example.quanlysach.dto.category.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO createCategory(CategoryRequest request);
    CategoryDTO updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
    CategoryDTO getCategoryById(Long id);
}
