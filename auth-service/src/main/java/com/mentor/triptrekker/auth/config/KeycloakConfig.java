package com.mentor.triptrekker.auth.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.admin.realm}")
    private String realm;

    @Value("${keycloak.admin.clientId}")
    private String clientId;

    @Value("${keycloak.admin.username}")
    private String username;

    @Value("${keycloak.admin.password}")
    private String password;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Bean
    public Keycloak keycloak() {
        return Keycloak.getInstance(
                serverUrl,
                realm,
                username,
                password,
                clientId);
    }
}