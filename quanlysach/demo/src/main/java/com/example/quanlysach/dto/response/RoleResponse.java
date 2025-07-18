package com.example.quanlysach.dto.response;

import lombok.Data;
import java.util.Set;

@Data
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private Set<String> usernames;
    private Set<String> permissionNames;
}
