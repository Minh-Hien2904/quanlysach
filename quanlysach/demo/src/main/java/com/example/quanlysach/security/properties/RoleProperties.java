package com.example.quanlysach.security.properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class RoleProperties {
    private final Map<String, String> permissionMap = new HashMap<>();

    public RoleProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("role.properties").getInputStream());
        for (String key : properties.stringPropertyNames()) {
            String uri = "/" + key.replace(".", "/");
            permissionMap.put(uri, properties.getProperty(key));
        }
    }

    public String getRequiredRole(String uri) {
        return permissionMap.get(uri);
    }
}
