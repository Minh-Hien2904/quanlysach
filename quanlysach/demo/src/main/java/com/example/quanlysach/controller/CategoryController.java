package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.CategoryRequest;
import com.example.quanlysach.dto.response.CategoryResponse;
import com.example.quanlysach.service.category.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<List<CategoryResponse>> getAll(HttpServletRequest request) {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest requestBody,
                                                   HttpServletRequest request) {
        CategoryResponse created = categoryService.createCategory(requestBody);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody CategoryRequest requestBody,
                                                   HttpServletRequest request) {
        CategoryResponse updated = categoryService.updateCategory(id, requestBody);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       HttpServletRequest request) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id,
                                                    HttpServletRequest request) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
}
