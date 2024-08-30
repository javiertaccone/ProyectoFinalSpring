package org.proyect.appweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.proyect.appweb.domain.Film;
import org.proyect.appweb.dto.RatingDTO;
import org.proyect.appweb.services.FilmService;
import org.proyect.appweb.services.JwtService;
import org.proyect.appweb.services.UserService;
import org.proyect.serviciorest.dto.RatingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class FilmDetail {

    @Autowired
    private FilmService filmService;

    @Autowired UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/film/{id}")
    public String getFilmDetail(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        Film film = filmService.findByID(id);
        if (film == null) {
            model.addAttribute("error", "Película no encontrada");
            return "error";
        }

        HttpHeaders headers = jwtService.createHeadersWithJwt(request);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "http://localhost:8081/ratings/average/" + id;
        try {
            ResponseEntity<Double> response = restTemplate.exchange(url, HttpMethod.GET, entity, Double.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Double averageRating = response.getBody();
                model.addAttribute("averageRating", averageRating);
            } else {
                model.addAttribute("error", "Error al obtener el promedio de calificación: " + response.getStatusCodeValue());
            }

            Long userId = userService.getAuthenticatedUserId();

            String url2 = "http://localhost:8081/ratings/personalRating/" + id + "/" + userId;

            try{
                ResponseEntity<RatingResponseDTO> response2 = restTemplate.exchange(url2, HttpMethod.GET, entity, RatingResponseDTO.class);
                if (response2.getStatusCode().is2xxSuccessful()) {
                    RatingResponseDTO ratingResponseDTO = response2.getBody();
                    if (ratingResponseDTO  != null && ratingResponseDTO.getScore() > 0) {
                        model.addAttribute("personalRating", ratingResponseDTO.getScore());
                    }
                }
            } catch (HttpClientErrorException.NotFound e){
                model.addAttribute("personalRating", "Sin calificar aún");
            } catch (HttpClientErrorException e) {
                model.addAttribute("error", "Error al obtener la calificación personal: " + e.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", "Error de cliente al obtener promedio de calificación: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error inesperado al obtener promedio de calificación: " + e.getMessage());
        }

        model.addAttribute("film", film);
        return "filmDetail";
    }
  /*  personalRating averageRating*/
    @PostMapping("/film/{id}")
    public String puntuarFilm(@RequestParam("filmId") Long filmId,
                              @RequestParam("rating") int rating,
                              HttpServletRequest request,
                              Model model) {

        Long userId = userService.getAuthenticatedUserId();

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setFilmId(filmId);
        ratingDTO.setUserId(userId);
        ratingDTO.setScore(rating);

        HttpHeaders headers = jwtService.createHeadersWithJwt(request);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            String url = "http://localhost:8081/ratings/average/" + filmId;
            ResponseEntity<Double> response = restTemplate.exchange(url, HttpMethod.GET, entity, Double.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Double averageRating = response.getBody();
                model.addAttribute("averageRating", averageRating);
            }
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }

        try {
            String url2 = "http://localhost:8081/ratings/personalRating/" + filmId + "/" + userId;
            ResponseEntity<RatingResponseDTO> response2 = restTemplate.exchange(url2, HttpMethod.GET, entity, RatingResponseDTO.class);
            if (response2.getStatusCode().is2xxSuccessful()) {
                RatingResponseDTO ratingResponseDTO = response2.getBody();
                if (ratingResponseDTO != null && ratingResponseDTO.getScore() > 0) {
                    model.addAttribute("personalRating", ratingResponseDTO.getScore());
                }
            }
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }

        try {
            HttpEntity<RatingDTO> entity2 = new HttpEntity<>(ratingDTO,headers);
            String url = "http://localhost:8081/ratings/ratings";
            ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,entity2, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("guardado", "Se guardó la calificación");
            }
        } catch (HttpClientErrorException e) {
                model.addAttribute("errorcalificacion", "Ya calificaste este film");
        }

        Film film = filmService.findByID(filmId);
        System.out.println(film);
        if (film == null) {
            return "error";
        }

        model.addAttribute("film", film);

        return "filmDetail";
    }
}

