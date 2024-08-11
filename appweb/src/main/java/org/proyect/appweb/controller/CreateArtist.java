package org.proyect.appweb.controller;

import org.proyect.appweb.dto.ArtistDTO;
import org.proyect.appweb.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateArtist {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/createArtist")
    public String mostrarForm(Model model){
        model.addAttribute("artist", new ArtistDTO());
        return "createArtist";
    }

    @PostMapping("/createArtist")
    public String crearArtista(@ModelAttribute("artist") ArtistDTO artistDTO, Model model){
        try{
            artistService.registrarArtista(artistDTO);
            model.addAttribute("message", "Artista creado con éxito");
            return "createArtist";
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "createArtist";
        }  catch (Exception e){
            model.addAttribute("error2", "Error inesperado: " + e.getMessage());
            return "createArtist";
        }
    }
}
