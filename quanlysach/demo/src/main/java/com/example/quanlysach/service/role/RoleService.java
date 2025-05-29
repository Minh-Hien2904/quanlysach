package com.example.quanlysach.service.role;

import com.example.quanlysach.dto.role.RoleRequestDTO;
import com.example.quanlysach.dto.role.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO getRoleById(Long id);
    RoleResponseDTO createRole(RoleRequestDTO request);
    RoleResponseDTO updateRole(Long id, RoleRequestDTO request);
    void deleteRole(Long id);
}

