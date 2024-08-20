package org.proyect.serviciorest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class RestApiSecurityConfig {

    @Bean
    public SecurityFilterChain configureApiConfiguration(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                .securityMatcher("/ratings")
                .authorizeHttpRequests(
                        authorizeRequests ->
                            authorizeRequests.
                                    requestMatchers("/auth")
                                    .permitAll()
                                    .requestMatchers("/ratings/**")
                                    .authenticated())
                        .sessionManagement(httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS))
                        .cors(Customizer.withDefaults())
                        .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
