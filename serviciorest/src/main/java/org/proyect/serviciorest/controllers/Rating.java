package org.proyect.serviciorest.controllers;

import org.proyect.serviciorest.dto.RatingDTO;
import org.proyect.serviciorest.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class Rating {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<String> puntuarFilm(@RequestBody RatingDTO ratingDTO){
        try {
            ratingService.save(ratingDTO);
        }
    }
}
