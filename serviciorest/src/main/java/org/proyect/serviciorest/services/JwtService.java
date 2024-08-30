package org.proyect.serviciorest.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtService {

    Jwt generateToken(UserDetails userDetails);


}
