package com.example.quanlysach.repository;

import com.example.quanlysach.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
