package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.request.PermissionRequest;
import com.example.quanlysach.dto.response.PermissionResponse;
import com.example.quanlysach.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionResponse toResponse(Permission permission) {
        return PermissionResponse.builder()
                .id(permission.getId())
                .code(permission.getCode())
                .name(permission.getName())
                .description(permission.getDescription())
                .method(permission.getMethod())
                .path(permission.getPath())
                .build();
    }

    public Permission toEntity(PermissionRequest request) {
        return Permission.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .method(request.getMethod())
                .path(request.getPath())
                .build();
    }
}
