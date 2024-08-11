package org.proyect.appweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Menu {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
