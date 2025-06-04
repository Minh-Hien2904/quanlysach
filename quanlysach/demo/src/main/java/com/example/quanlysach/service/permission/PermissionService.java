package com.example.quanlysach.service.permission;

import com.example.quanlysach.dto.request.PermissionRequest;
import com.example.quanlysach.dto.response.PermissionResponse;
import org.springframework.data.domain.Page;

public interface PermissionService {
    Page<PermissionResponse> getAll(int page, int size, String keyword);
    PermissionResponse getById(Long id);
    PermissionResponse create(PermissionRequest dto);
    PermissionResponse update(Long id, PermissionRequest dto);
    void delete(Long id);
}