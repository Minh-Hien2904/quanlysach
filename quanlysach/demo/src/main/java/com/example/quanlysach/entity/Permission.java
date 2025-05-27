package com.example.quanlysach.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code; // Ví dụ: api.v1.library.permission.create

    @Column(nullable = false)
    private String name; // Ví dụ: Thêm mới

    @Column
    private String description; // Ví dụ: Mô tả chi tiết quyền
}
