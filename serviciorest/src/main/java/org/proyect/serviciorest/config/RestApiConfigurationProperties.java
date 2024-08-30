package org.proyect.serviciorest.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Getter
@Component
@ConfigurationProperties(prefix = "application.rest")
public class RestApiConfigurationProperties {
    private String baseUrl;
    private Path basePath;
    private List<User> users;

    public void setBaseUrl(String baseUrl) { // <-- Y agrega este setter
        this.baseUrl = baseUrl;
    }

    public void setBasePath(Path basePath) {
        this.basePath = basePath;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Path getPath(String file) {
        return Path.of(basePath.toString(), file);
    }

    @Getter
    public static class User {
        private String username;
        private String password;
        private List<String> authorities;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setAuthorities(List<String> authorities) {
            this.authorities = authorities;
        }
    }
}
