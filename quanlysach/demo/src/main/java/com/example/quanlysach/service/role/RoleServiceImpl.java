package com.example.quanlysach.service.role;

import com.example.quanlysach.dto.request.RoleRequest;
import com.example.quanlysach.dto.response.RoleResponse;
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
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Set<User> users = fetchUsersByIds(request.getUserIds());
        Set<Permission> permissions = fetchPermissionsByIds(request.getPermissionIds());
        Role role = roleMapper.toEntity(request, users, permissions);
        return roleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Set<User> users = fetchUsersByIds(request.getUserIds());
        Set<Permission> permissions = fetchPermissionsByIds(request.getPermissionIds());
        roleMapper.updateEntity(role, request, users, permissions);
        return roleMapper.toResponse(roleRepository.save(role));
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
