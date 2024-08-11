package org.proyect.appweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Logout {

    @GetMapping("/logout")
    public String cerrarSesion(){
        return "home";
    }
}
