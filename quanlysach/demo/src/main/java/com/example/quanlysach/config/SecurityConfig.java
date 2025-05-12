package com.example.quanlysach.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("classpath:role.properties")
    private final Properties roleProperties;

    public SecurityConfig(Properties roleProperties) {
        this.roleProperties = roleProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/db-check").hasAnyAuthority(getRoles())
                        .anyRequest().authenticated()
                )
                .build();
    }

    private String[] getRoles() {
        String roles = roleProperties.getProperty("role.admin");
        return roles != null ? roles.split(",") : new String[]{};
    }

}