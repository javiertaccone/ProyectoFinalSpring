package org.proyect.appweb.config;

import org.proyect.appweb.domain.Rol;
import org.proyect.appweb.domain.User;
import org.proyect.appweb.repositories.RolDAO;
import org.proyect.appweb.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdminUserInitializer {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdminUser() {
        return args -> {
            if (!userDAO.existsByUsername("admin")) {
                Rol rolAdmin = rolDAO.findByRolType("admin");
                if (rolAdmin == null) {
                    rolAdmin = new Rol();
                    rolAdmin.setRolType("admin");
                    rolDAO.save(rolAdmin);
                }

                User admin = new User();
                admin.setUsername("admin");
                admin.setName("Admin");
                admin.setSurname("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setBirthDate(new Date());
                admin.setRol(rolAdmin);

                userDAO.save(admin);
            }
        };
    }
}