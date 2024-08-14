package org.proyect.appweb.controller;

import org.proyect.appweb.domain.Film;
import org.proyect.appweb.dto.RatingDTO;
import org.proyect.appweb.services.FilmService;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
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

        String url = "http://localhost:8081/ratings/average/" + id;
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Double averageRating = response.getBody();
            model.addAttribute("film", film);
            model.addAttribute("averageRating", averageRating);
        } else {
            model.addAttribute("film", film);
            model.addAttribute("error", "Error al obtener el promedio de calificación: " + response.getStatusCodeValue());
        }

        Long userId = userService.getAuthenticatedUserId();
        String url2 = "http://localhost:8081/ratings/personalRating/" + id + "/" + userId;
        ResponseEntity<Integer> response2 = restTemplate.getForEntity(url2, Integer.class);

        if (response2.getStatusCode().is2xxSuccessful()) {
            Integer personalRating = response2.getBody();
            if (personalRating != null && personalRating > 0) {
                model.addAttribute("personalRating", personalRating);
            }
        }

        return "filmDetail";
    }

    @PostMapping("/film/{id}")
    public String puntuarFilm(@RequestParam("filmId") Long filmId,
                              @RequestParam("rating") int rating,
                              Model model) {

        Long userId = userService.getAuthenticatedUserId();

        String url = "http://localhost:8081/ratings/ratings";

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setFilmId(filmId);
        ratingDTO.setUserId(userId);
        ratingDTO.setScore(rating);


        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, ratingDTO, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("success", "Se guardó la calificación");
            } else if (response.getStatusCode().value() == HttpStatus.CONFLICT.value()) {
                model.addAttribute("error", "El usuario ya calificó el film");
            } else {
                model.addAttribute("error", "Ocurrió un error al calificar la película: " + response.getStatusCode().value());
            }
        } catch (HttpClientErrorException e) {
            // Captura y maneja errores específicos de HTTP
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                model.addAttribute("error", "El usuario ya calificó el film");
            } else {
                model.addAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
            }
        } catch (Exception e) {
            // Captura y maneja otros errores generales
            model.addAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
        }

        Film film = filmService.findByID(filmId);
        if (film == null) {
            return "error";
        }
        model.addAttribute("film", film);

        return "filmDetail";
    }
}

