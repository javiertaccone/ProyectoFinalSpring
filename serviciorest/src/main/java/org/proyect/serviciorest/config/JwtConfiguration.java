package org.proyect.serviciorest.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableConfigurationProperties (JwtConfigurationProperties.class)
@RequiredArgsConstructor
public class JwtConfiguration {

    private  final JwtConfigurationProperties jwtConfigurationProperties;

    @Bean
    public NimbusJwtEncoder nimbusJwtEncoder(){
        return new NimbusJwtEncoder(
                new ImmutableSecret<>(
                    new SecretKeySpec(jwtConfigurationProperties.secret().getBytes(),  "HMAC")));
    }

    @Bean
    public NimbusJwtDecoder nimbusJwtDecoder(){
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(jwtConfigurationProperties.secret().getBytes(), "HMAC"))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
