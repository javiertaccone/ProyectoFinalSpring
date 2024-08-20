package org.proyect.serviciorest.security;

import lombok.RequiredArgsConstructor;
import org.proyect.serviciorest.config.RestApiConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestApiUserDetailsServiceConfiguration {

    private final RestApiConfigurationProperties restApiConfigurationProperties;

    @Bean
    public UserDetailsService restUserDetailsService(){

            List<UserDetails> users = restApiConfigurationProperties.getUsers()
                    .stream().map(user ->
                            User.builder()
                                    .username(user.getUsername())
                                    .password(user.getPassword())
                                    .authorities(user.getAuthorities().toArray(new String[0]))
                                    .build())
                    .toList();
            return new InMemoryUserDetailsManager(users);

    }
}
