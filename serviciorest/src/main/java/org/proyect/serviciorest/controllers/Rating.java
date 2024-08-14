package org.proyect.serviciorest.controllers;

import org.proyect.serviciorest.dto.RatingDTO;
import org.proyect.serviciorest.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public int getPersonalRating(@PathVariable("filmId")Long filmID,
                                 @PathVariable("userId")Long userId){
        if (ratingService.usuarioYaCalifico(userId, filmID)) {
            return ratingService.buscarScore(userId, filmID);
        } else {
            return -1;
        }
    }

    @PostMapping("/ratings")
    public ResponseEntity<String> recibirRating(@RequestBody RatingDTO ratingDTO) {
        try {
            if (!ratingService.usuarioYaCalifico(ratingDTO.getUserId(), ratingDTO.getFilmId())) {
                ratingService.registerRating(ratingDTO);
                return ResponseEntity.ok("Film calificado");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El usuario ya calificó el film");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la calificación");
        }
    }


}
