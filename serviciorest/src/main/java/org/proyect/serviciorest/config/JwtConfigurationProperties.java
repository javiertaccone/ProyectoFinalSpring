package org.proyect.serviciorest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.Duration;

@ConfigurationProperties(prefix = "application.jwt")
public record JwtConfigurationProperties (String secret, Duration duration)  {
}
