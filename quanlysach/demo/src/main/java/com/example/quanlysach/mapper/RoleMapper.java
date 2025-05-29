package com.example.quanlysach.mapper;

import com.example.quanlysach.dto.role.RoleRequestDTO;
import com.example.quanlysach.dto.role.RoleResponseDTO;
import com.example.quanlysach.entity.ERole;
import com.example.quanlysach.entity.Permission;
import com.example.quanlysach.entity.Role;
import com.example.quanlysach.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleResponseDTO toResponseDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName().name());
        dto.setDescription(role.getDescription());
        dto.setUsernames(role.getUsers().stream()
                .map(User::getUsername)
                .collect(Collectors.toSet()));
        dto.setPermissionNames(role.getPermissions().stream()
                .map(Permission::getName)
                .collect(Collectors.toSet()));
        return dto;
    }

    public Role toEntity(RoleRequestDTO dto, Set<User> users, Set<Permission> permissions) {
        Role role = new Role();
        role.setName(ERole.valueOf(dto.getName()));
        role.setDescription(dto.getDescription());
        role.setUsers(users);
        role.setPermissions(permissions);
        return role;
    }

    public void updateEntity(Role role, RoleRequestDTO dto, Set<User> users, Set<Permission> permissions) {
        role.setName(ERole.valueOf(dto.getName()));
        role.setDescription(dto.getDescription());
        role.setUsers(users);
        role.setPermissions(permissions);
    }
}
