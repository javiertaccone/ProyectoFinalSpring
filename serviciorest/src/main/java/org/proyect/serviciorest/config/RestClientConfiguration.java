package org.proyect.serviciorest.config;

import lombok.RequiredArgsConstructor;
import org.proyect.serviciorest.config.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfiguration {

    private final RestApiConfigurationProperties restApiConfigurationProperties;
    private final AuthService authService;

    @Bean
    public RestClient restClient(){

        return RestClient.builder()
                .baseUrl(restApiConfigurationProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .requestInitializer(request -> request.getHeaders()
                            .add(HttpHeaders.AUTHORIZATION, "Bearer" + authService.getAccessToken()))
                .build();
    }

}
