package org.proyect.appweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.proyect.appweb.domain.Artist;
import org.proyect.appweb.domain.Film;
import org.proyect.appweb.domain.TypeArtist;
import org.proyect.appweb.dto.EditFilmDTO;
import org.proyect.appweb.dto.FilmDTO;
import org.proyect.appweb.services.ArtistService;
import org.proyect.appweb.services.FilmService;
import org.proyect.appweb.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EditFilm {

    @Autowired
    private FilmService filmService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/editFilm/{id}")
    public String editarFilm(@PathVariable("id") Long filmId, HttpServletRequest request, Model model) {
        HttpHeaders headers = jwtService.createHeadersWithJwt(request);
        List<Artist> directors = artistService.findAllByTypeArtist(TypeArtist.DIRECTOR);
        List<Artist> actors = artistService.findAllByTypeArtist(TypeArtist.ACTOR);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "http://localhost:8081/edit";
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Film film = filmService.findByID(filmId);
                if (film == null) {
                    model.addAttribute("error", "Película no encontrada");
                    return "redirect:/error";
                }

                EditFilmDTO editFilmDTO = new EditFilmDTO();
                editFilmDTO.setId(film.getId());
                editFilmDTO.setTitle(film.getTitle());
                editFilmDTO.setReleaseYear(film.getReleaseYear());
                editFilmDTO.setPoster(film.getPoster());
                editFilmDTO.setDirectorID(film.getDirector().getId());
                editFilmDTO.setActorsID(film.getActors().stream().map(Artist::getId).collect(Collectors.toList()));

                System.out.println("editFilm ID " + editFilmDTO.getId());
                System.out.println("editFilm TITLE " + editFilmDTO.getTitle());
                System.out.println("editFilm YEAR " + editFilmDTO.getReleaseYear());
                System.out.println("editFilm POSTER " + editFilmDTO.getPoster());


                model.addAttribute("film", film);
                model.addAttribute("editFilm", editFilmDTO);
                model.addAttribute("directors", directors);
                model.addAttribute("actors", actors);

                return "editFilm";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Usuario no autorizado: " + e.getMessage());
        }
        return "redirect:/error";
    }

    @PostMapping("/editFilm")
    public String editarFilm(@ModelAttribute("editFilm") EditFilmDTO editFilmDTO, Model model) {
        try {
            filmService.editarFilm(editFilmDTO);
            model.addAttribute("message", "Film modificado con éxito");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        Film film = filmService.findByID(editFilmDTO.getId());
        List<Artist> directors = artistService.findAllByTypeArtist(TypeArtist.DIRECTOR);
        List<Artist> actors = artistService.findAllByTypeArtist(TypeArtist.ACTOR);

        model.addAttribute("film", film);
        model.addAttribute("directors", directors);
        model.addAttribute("actors", actors);
        return "editFilm";
    }
}
