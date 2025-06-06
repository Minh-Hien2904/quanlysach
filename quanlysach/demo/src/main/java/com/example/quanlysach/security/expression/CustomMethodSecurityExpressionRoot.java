package com.example.quanlysach.security.expression;

import com.example.quanlysach.security.properties.RoleProperties;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import jakarta.servlet.http.HttpServletRequest;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    private final RoleProperties roleProperties;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, RoleProperties roleProperties) {
        super(authentication);
        this.roleProperties = roleProperties;
    }

    public boolean fileRole(HttpServletRequest request) {
        String uri = request.getRequestURI(); // VD: /api/v1/library/book/create
        String requiredRole = roleProperties.getRequiredRole(uri);
        if (requiredRole == null) {
            return false;
        }

        return this.getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(auth -> auth.equals(requiredRole));
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    public void setThis(Object target) {
        this.target = target;
    }
}
