package org.proyect.serviciorest.services;

import org.proyect.serviciorest.dto.AuthenticationMeResponseDTO;
import org.proyect.serviciorest.dto.AuthenticationRequestDTO;
import org.proyect.serviciorest.dto.AuthenticationResponseDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequesDTO );

    AuthenticationMeResponseDTO getAuthenticated();

}
