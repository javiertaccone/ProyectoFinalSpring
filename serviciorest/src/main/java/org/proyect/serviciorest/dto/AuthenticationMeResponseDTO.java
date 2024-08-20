package org.proyect.serviciorest.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class AuthenticationMeResponseDTO {

    String username;
    List<String> authorities;
}
