package com.example.quanlysach.service.permission;

import com.example.quanlysach.dto.request.PermissionRequest;
import com.example.quanlysach.dto.response.PermissionResponse;
import com.example.quanlysach.entity.Permission;
import com.example.quanlysach.mapper.PermissionMapper;
import com.example.quanlysach.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public Page<PermissionResponse> getAll(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Permission> permissions = (keyword == null || keyword.isBlank())
                ? permissionRepository.findAll(pageable)
                : permissionRepository.findAll((Specification<Permission>) (root, query, cb) ->
                cb.or(
                        cb.like(root.get("name"), "%" + keyword + "%"),
                        cb.like(root.get("code"), "%" + keyword + "%")
                ), pageable);

        return permissions.map(permissionMapper::toResponse);
    }

    @Override
    public PermissionResponse getById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));
        return permissionMapper.toResponse(permission);
    }

    @Override
    public PermissionResponse create(PermissionRequest dto) {
        if (permissionRepository.findByCode(dto.getCode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Permission code already exists");
        }
        Permission permission = permissionMapper.toEntity(dto);
        return permissionMapper.toResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse update(Long id, PermissionRequest dto) {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found"));

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setMethod(dto.getMethod());
        existing.setPath(dto.getPath());

        return permissionMapper.toResponse(permissionRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found");
        }
        permissionRepository.deleteById(id);
    }
}
