package com.example.quanlysach.dto.role;

import lombok.Data;
import java.util.Set;

@Data
public class RoleResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Set<String> usernames;
    private Set<String> permissionNames;
}
