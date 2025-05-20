package com.example.quanlysach.service;

import com.example.quanlysach.dto.category.CategoryDTO;
import com.example.quanlysach.dto.category.CategoryRequest;
import com.example.quanlysach.entity.Category;
import com.example.quanlysach.mapper.CategoryMapper;
import com.example.quanlysach.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Tên thể loại đã tồn tại");
        }
        Category category = Category.builder()
                .name(request.getName())
                .build();
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));

        category.setName(request.getName());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy thể loại");
        }
        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
        return categoryMapper.toDTO(category);
    }
}
