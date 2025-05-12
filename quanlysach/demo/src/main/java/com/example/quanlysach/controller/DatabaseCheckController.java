package com.example.quanlysach.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequestMapping("/api/db-check")
public class DatabaseCheckController {

    private final DataSource dataSource;

    public DatabaseCheckController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping
    public String checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                return "Kết nối MariaDB thành công";
            } else {
                return "Không thể kết nối MariaDB";
            }
        } catch (Exception e) {
            return "Lỗi kết nối MariaDB: " + e.getMessage();
        }
    }
}
