package org.proyect.appweb.controller;

import org.proyect.appweb.domain.Film;
import org.proyect.appweb.dto.RatingDTO;
import org.proyect.appweb.services.FilmService;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class FilmDetail {

    @Autowired
    private FilmService filmService;

    @Autowired UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/film/{id}")
    public String getFilmDetail(@PathVariable("id") Long id, Model model) {
        Film film = filmService.findByID(id);
        if (film == null) {
            return "error";
        }
        model.addAttribute("film", film);
        return "filmDetail";
    }

    @PostMapping("/puntuarFilm")
     public String puntuarFilm(@RequestParam("filmID") Long filmId,
                               @RequestParam("rating") int rating,
                               Model model){

        Long userId = userService.getAuthenticatedUserId();

        String url = "http://localhost:8081/ratings";

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setFilmId(filmId);
        ratingDTO.setUserId(userId);
        ratingDTO.setScore(rating);

        ResponseEntity<String> response = restTemplate.postForEntity(url, ratingDTO, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("success", "Se guardó la calificación");
        } else {
            model.addAttribute("error", "No se guardó");
        }
        return "redirect:/film/" + filmId;
    }
}

