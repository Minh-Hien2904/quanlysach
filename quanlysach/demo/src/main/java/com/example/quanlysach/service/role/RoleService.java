package com.example.quanlysach.service.role;

import com.example.quanlysach.dto.request.RoleRequest;
import com.example.quanlysach.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse getRoleById(Long id);
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(Long id, RoleRequest request);
    void deleteRole(Long id);
}
