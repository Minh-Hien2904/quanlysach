package com.example.quanlysach.config;

import com.example.quanlysach.security.expression.CustomMethodSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private CustomMethodSecurityExpressionHandler customHandler;

    @Override
    protected CustomMethodSecurityExpressionHandler createExpressionHandler() {
        return customHandler;
    }
}