package org.proyect.serviciorest.services;

import org.proyect.serviciorest.dto.RatingDTO;


public interface RatingService {
    void crearRating(RatingDTO ratingDTO);

    double obtenerMedia(Long filmId);

    boolean usuarioYaCalifico(Long userId, Long filmId);
}
