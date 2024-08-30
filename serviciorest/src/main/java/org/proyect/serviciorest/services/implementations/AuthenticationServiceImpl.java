package org.proyect.serviciorest.services.implementations;

import lombok.RequiredArgsConstructor;
import org.proyect.serviciorest.dto.AuthenticationMeResponseDTO;
import org.proyect.serviciorest.dto.AuthenticationRequestDTO;
import org.proyect.serviciorest.dto.AuthenticationResponseDTO;
import org.proyect.serviciorest.services.AuthenticationService;
import org.proyect.serviciorest.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO)  {

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                        authenticationRequestDTO.getPassword()));

        System.out.println("authentication.isAuthenticated() " + authentication.isAuthenticated());


        UserDetails userDetails = (UserDetails) authentication.getPrincipal() ;

        Jwt jwt = jwtService.generateToken(userDetails);

        return AuthenticationResponseDTO.builder()
                .accessToken(jwt.getTokenValue())
                .expiresIn(ChronoUnit.SECONDS.between(Instant.now(), jwt.getExpiresAt()) +1)
                .build();
    }

    @Override
    public AuthenticationMeResponseDTO getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return AuthenticationMeResponseDTO
                .builder()
                .username(authentication.getName())
                .authorities(
                        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                ).build();

    }
}
