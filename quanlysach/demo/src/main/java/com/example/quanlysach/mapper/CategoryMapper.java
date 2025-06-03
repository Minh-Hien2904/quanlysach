package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.request.CategoryRequest;
import com.example.quanlysach.dto.response.CategoryResponse;
import com.example.quanlysach.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryResponse(
                category.getId(),
                category.getCode(),
                category.getName()
        );
    }

    public Category toEntity(CategoryRequest request) {
        if (request == null) {
            return null;
        }
        return Category.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    // Optional: cập nhật entity từ request (dùng cho update)
    public void updateEntityFromRequest(CategoryRequest request, Category category) {
        if (request == null || category == null) return;
        category.setCode(request.getCode());
        category.setName(request.getName());
    }
}
