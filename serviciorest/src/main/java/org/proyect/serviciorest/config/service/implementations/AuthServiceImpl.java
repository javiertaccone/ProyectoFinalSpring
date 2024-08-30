package org.proyect.serviciorest.config.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.proyect.serviciorest.config.RestApiConfigurationProperties;
import org.proyect.serviciorest.config.service.AuthService;
import org.proyect.serviciorest.dto.AuthenticationResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestApiConfigurationProperties restApiConfigurationProperties;
    private final RestClient restClient = RestClient.builder().build();

    private String accessToken;
    private long expiresAt;

    @Override
    public String getAccessToken() {
        if(System.currentTimeMillis() < expiresAt){
            return accessToken;
        }

         Map<String, String> authrequest =
                 Map.of(
                    "username",
                    restApiConfigurationProperties.getUsers().get(0).getUsername(),
                    "password",
                    restApiConfigurationProperties.getUsers().get(0).getPassword());
        try {
               AuthenticationResponseDTO authenticationResponseDTO = restClient
                    .post()
                    .uri(restApiConfigurationProperties.getBaseUrl() + "/ratings/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(authrequest)
                    .retrieve()
                    .body(AuthenticationResponseDTO.class);

                    accessToken = authenticationResponseDTO.getAccessToken();
                    expiresAt = authenticationResponseDTO.getExpiresIn();

        } catch (Exception e){
            log.error("Exception in file-rest auth endpoint", e);
        }
        return accessToken;
    }
    private record AuthRespondeDTO(String accessToken, int expiresIn){
        public long getExpiresAt(){
            return System.currentTimeMillis() + expiresIn * 1000L - 5 * 1000L;
        }
    }
}
