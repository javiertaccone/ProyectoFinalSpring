package org.proyect.serviciorest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edit")
public class SecuredController {

    @GetMapping
    public ResponseEntity<String> validarAdmin(){
        return ResponseEntity.ok("Admin validado");
    }
}
