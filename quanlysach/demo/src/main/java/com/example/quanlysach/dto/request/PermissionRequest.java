package com.example.quanlysach.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequest {
    private String code;
    private String name;
    private String description;
    private String method;
    private String path;
}
