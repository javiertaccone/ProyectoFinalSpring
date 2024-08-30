package org.proyect.serviciorest.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.proyect.serviciorest.dto.RatingDTO;
import org.proyect.serviciorest.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ratings")
public class Rating {


    @Autowired
    private RatingService ratingService;

    @GetMapping("/average/{filmId}")
    public ResponseEntity<Double> calcularAverage(@PathVariable("filmId")Long filmID){
        double average = ratingService.calcularAverage(filmID);
        return ResponseEntity.ok(average);
    }

    @GetMapping("/personalRating/{filmId}/{userId}")
    public ResponseEntity<Object> getPersonalRating(@PathVariable("filmId") Long filmId,
                                                    @PathVariable("userId") Long userId) {
        if (!ratingService.usuarioYaCalifico(userId, filmId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Valoración no encontrada");
        }

        int score = ratingService.buscarScore(userId, filmId);
        return ResponseEntity.ok().body(Map.of("score", score));
    }

    @PostMapping("/ratings")
    public ResponseEntity<String> recibirRating(@RequestBody RatingDTO ratingDTO) {
        if (ratingDTO.getUserId() == null || ratingDTO.getFilmId() == null || ratingDTO.getScore() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos");
        }

        try {
            if (!ratingService.usuarioYaCalifico(ratingDTO.getUserId(), ratingDTO.getFilmId())) {
                ratingService.registerRating(ratingDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body("Film calificado");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya calificó el film");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la calificación");
        }
    }
}
