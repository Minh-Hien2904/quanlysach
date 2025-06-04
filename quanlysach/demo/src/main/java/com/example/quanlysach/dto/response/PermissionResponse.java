package com.example.quanlysach.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String method;
    private String path;
}
