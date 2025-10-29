package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "keycloak",  ignoreUnknownFields = false)
public class KeycloakProps {

    /**
     * keycloak realm name
     */
    private String realm;

    /**
     * server endpoint
     */
    private String endpoint;

    /**
     * Keycloak client properties
     */
    private final Application application = new Application();


    @Getter
    @Setter
    public static class Application {
        private String clientId;
        private String clientSecret;
    }
}
