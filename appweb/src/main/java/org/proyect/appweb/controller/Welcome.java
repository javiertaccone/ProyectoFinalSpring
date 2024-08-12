package org.proyect.appweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Welcome {

    @GetMapping("/welcome")
    public String login() {
        return "welcome";
    }
}
