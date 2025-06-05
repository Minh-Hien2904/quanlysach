//package com.example.quanlysach.config;
//
//import com.example.quanlysach.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.Properties;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Value("classpath:role.properties")
//    private final Properties roleProperties;
//
//    private final JwtUtil jwtUtil;
//
//    public SecurityConfig(Properties roleProperties, JwtUtil jwtUtil) {
//        this.roleProperties = roleProperties;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/db-check").hasAnyAuthority(getRoles())
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/admin/**").hasAuthority(roleProperties.getProperty("role.admin"))
//                        .requestMatchers("/api/user/**").hasAuthority(roleProperties.getProperty("role.user"))
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtUtil.jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//    private String[] getRoles() {
//        String roles = roleProperties.getProperty("role.admin") + "," + roleProperties.getProperty("role.user");
//        return roles.split(",");
//    }
//}
