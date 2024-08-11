package org.proyect.appweb.controller;

import org.proyect.appweb.domain.Artist;
import org.proyect.appweb.domain.Film;
import org.proyect.appweb.domain.TypeArtist;
import org.proyect.appweb.dto.FilmDTO;
import org.proyect.appweb.services.ArtistService;
import org.proyect.appweb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CreateFilm {

    @Autowired
    private FilmService filmService;

    @Autowired
    private ArtistService artistService;

    @GetMapping("/createFilm")
    public String mostrarForm(Model model){
        List<Artist> directors = artistService.findAllByTypeArtist(TypeArtist.DIRECTOR);
        List<Artist> actors = artistService.findAllByTypeArtist(TypeArtist.ACTOR);

        model.addAttribute("directors", directors);
        model.addAttribute("actors", actors);
        model.addAttribute("film", new FilmDTO());

        return "createFilm";
    }

    @PostMapping("/createFilm")
    public String crearFilm(@ModelAttribute("film")FilmDTO filmDTO, Model model){
        try {
            filmService.registrarFilm(filmDTO);
            model.addAttribute("message" , "Film creado conéxito" );
            return "createFilm";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "createFilm";
        }
    }
}
