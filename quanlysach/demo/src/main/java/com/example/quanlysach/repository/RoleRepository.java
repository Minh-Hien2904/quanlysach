package com.example.quanlysach.repository;

import com.example.quanlysach.entity.ERole;
import com.example.quanlysach.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
