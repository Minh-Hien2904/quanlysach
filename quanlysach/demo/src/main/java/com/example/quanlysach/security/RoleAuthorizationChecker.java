package com.example.quanlysach.security;

import com.example.quanlysach.config.RolePropertiesLoader;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("fileRole")
@RequiredArgsConstructor
public class RoleAuthorizationChecker {

    private final RolePropertiesLoader rolePropertiesLoader;

    public boolean check(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String roleRequired = rolePropertiesLoader.getUriRoleMap().get(uri);

        if (roleRequired == null) {
            return false;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleRequired));
    }
}
