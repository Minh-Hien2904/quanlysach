package com.example.quanlysach.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data                   // tự động tạo getter/setter/toString/equals/hashCode
@NoArgsConstructor      // constructor không tham số
@AllArgsConstructor     // constructor có tất cả tham số
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String identityNumber;
    private int age;
    private String birthday;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
