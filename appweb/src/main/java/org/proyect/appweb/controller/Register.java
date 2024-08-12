package org.proyect.appweb.controller;

import org.proyect.appweb.dto.UserRegisterDTO;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Register {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String mostrarForm(Model model){
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String guardarNuevoUsuario(@ModelAttribute("user") UserRegisterDTO userRegisterDTO, Model model){
        try {
            userService.registerUser(userRegisterDTO);
            model.addAttribute("message", "Usuario registrado con éxito");
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
}
