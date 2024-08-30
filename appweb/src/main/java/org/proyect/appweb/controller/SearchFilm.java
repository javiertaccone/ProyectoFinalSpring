package org.proyect.appweb.controller;

import org.proyect.appweb.domain.Film;
import org.proyect.appweb.dto.SearchDTO;
import org.proyect.appweb.services.FilmService;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class SearchFilm {

    @Autowired
    private FilmService filmService;

    @Autowired
    UserService userService;

    @GetMapping("/searchFilm")
    public String mostrarBuscador(Model model){
        Long userId = userService.getAuthenticatedUserId();
        String rol = userService.getRoltyperUserById(userId);

        try{
            List<Film> films = filmService.defaultCharge();
            model.addAttribute("films", films);
            model.addAttribute("title", new String());
            if(!Objects.equals(rol, "user")){
                System.out.println("ROL: " + rol);
                model.addAttribute("rol", rol);
            }
            return "searchFilm";
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
        }
        return "searchFilm";
    }

    @PostMapping("/searchFilm")
        public String buscarFilm(@RequestParam("title") String title, Model model) {
        try {
            List<Film> films = filmService.buscarFilm(title);
            model.addAttribute("films", films);
            return "searchFilm";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "searchFilm";
        }
    }
}
