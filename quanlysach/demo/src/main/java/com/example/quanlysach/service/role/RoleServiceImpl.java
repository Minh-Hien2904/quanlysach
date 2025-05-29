package com.example.quanlysach.service.role;

import com.example.quanlysach.dto.role.RoleRequestDTO;
import com.example.quanlysach.dto.role.RoleResponseDTO;
import com.example.quanlysach.entity.Permission;
import com.example.quanlysach.entity.Role;
import com.example.quanlysach.entity.User;
import com.example.quanlysach.mapper.RoleMapper;
import com.example.quanlysach.repository.PermissionRepository;
import com.example.quanlysach.repository.RoleRepository;
import com.example.quanlysach.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return roleMapper.toResponseDTO(role);
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO request) {
        Set<User> users = fetchUsersByIds(request.getUserIds());
        Set<Permission> permissions = fetchPermissionsByIds(request.getPermissionIds());
        Role role = roleMapper.toEntity(request, users, permissions);
        return roleMapper.toResponseDTO(roleRepository.save(role));
    }

    @Override
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Set<User> users = fetchUsersByIds(request.getUserIds());
        Set<Permission> permissions = fetchPermissionsByIds(request.getPermissionIds());
        roleMapper.updateEntity(role, request, users, permissions);
        return roleMapper.toResponseDTO(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }

    private Set<User> fetchUsersByIds(Set<Long> ids) {
        if (ids == null) return new HashSet<>();
        return new HashSet<>(userRepository.findAllById(ids));
    }

    private Set<Permission> fetchPermissionsByIds(Set<Long> ids) {
        if (ids == null) return new HashSet<>();
        return new HashSet<>(permissionRepository.findAllById(ids));
    }
}
