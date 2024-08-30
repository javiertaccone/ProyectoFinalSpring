package org.proyect.appweb.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Getter
@Component
@ConfigurationProperties(prefix = "application.rest")
public class RestUserConfig {

    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUsernameByRole(String rol) {
        if (rol.equals("user")){
            return users.get(0).username;
        } else if (rol.equals("admin")) {
            return users.get(1).username;
        } return null;
    }

    public String getPasswordByRole(String rol) {
        if (rol.equals("user")){
            return users.get(0).password;
        } else if (rol.equals("admin")) {
            return users.get(1).password;
        } return null;
    }

    @Getter
    public static class User {
        private String username;
        private String password;
        private String authorities;


        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setAuthorities(String authorities) {
            this.authorities = authorities;
        }
    }
}
