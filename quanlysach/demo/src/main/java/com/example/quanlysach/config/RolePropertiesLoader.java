package com.example.quanlysach.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Getter
@Component
public class RolePropertiesLoader {

    private final Map<String, String> uriRoleMap = new HashMap<>();

    @PostConstruct
    public void loadRoleMappings() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("role.properties").getInputStream());

        for (String key : properties.stringPropertyNames()) {
            uriRoleMap.put(key, properties.getProperty(key));
        }

        log.info("Loaded role mappings: {}", uriRoleMap);
    }
}
