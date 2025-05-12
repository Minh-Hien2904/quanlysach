package com.example.quanlysach.model;

public class User {
    // Các thuộc tính của User
    private String username;
    private String password;

    // Constructor mặc định (không tham số)
    public User() {
        // Khởi tạo mặc định, có thể để trống hoặc gán giá trị mặc định
    }

    // Constructor có tham số (nếu cần)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter và Setter cho các thuộc tính
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Phương thức khác (nếu cần)
    public void displayUserInfo() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
