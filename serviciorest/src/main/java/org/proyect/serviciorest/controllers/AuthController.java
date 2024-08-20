package org.proyect.serviciorest.controllers;

import lombok.RequiredArgsConstructor;
import org.proyect.serviciorest.dto.AuthenticationMeResponseDTO;
import org.proyect.serviciorest.dto.AuthenticationRequestDTO;
import org.proyect.serviciorest.dto.AuthenticationResponseDTO;
import org.proyect.serviciorest.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> postAuthenticate(
            @RequestBody AuthenticationRequestDTO authenticationRequestDTO){

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthenticationMeResponseDTO> getAuthenticated(){
        return ResponseEntity.ok(authenticationService.getAuthenticated());
    }
}
