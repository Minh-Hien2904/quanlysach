package com.example.quanlysach.controller;

import com.example.quanlysach.dto.request.PermissionRequest;
import com.example.quanlysach.service.permission.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String keyword,
                                    HttpServletRequest request) {
        return ResponseEntity.ok(permissionService.getAll(page, size, keyword));
    }

    @GetMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(permissionService.getById(id));
    }

    @PostMapping
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> create(@RequestBody PermissionRequest dto, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody PermissionRequest dto,
                                    HttpServletRequest request) {
        return ResponseEntity.ok(permissionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("fileRole(#request)")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}