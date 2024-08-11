package org.proyect.appweb.controller;

import org.proyect.appweb.dto.UserLoginDTO;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Login {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String mostrarAcceso(Model model){
        model.addAttribute("datosAcceso", new UserLoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String validarAccesos(@ModelAttribute("datosAcceso") UserLoginDTO userLoginDTO, Model model){
        try{
            userService.validarDatosAcceso(userLoginDTO);
            return "welcome";
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }


}
