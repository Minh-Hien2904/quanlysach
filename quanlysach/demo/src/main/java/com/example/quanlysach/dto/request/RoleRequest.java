package com.example.quanlysach.dto.request;

import lombok.Data;
import java.util.Set;

@Data
public class RoleRequest {
    private String name;
    private String description;
    private Set<Long> userIds;
    private Set<Long> permissionIds;
}