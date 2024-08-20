package org.proyect.appweb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.rest")
public record RestApiConfigurationProperties(String baseUrl, String username, String password)
{
  }
