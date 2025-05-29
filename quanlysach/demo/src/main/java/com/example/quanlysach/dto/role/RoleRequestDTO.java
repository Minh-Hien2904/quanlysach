package com.example.quanlysach.dto.role;

import lombok.Data;
import java.util.Set;

@Data
public class RoleRequestDTO {
    private String name;
    private String description;
    private Set<Long> userIds;
    private Set<Long> permissionIds;
}

