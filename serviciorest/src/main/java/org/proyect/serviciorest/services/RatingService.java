package org.proyect.serviciorest.services;

import org.proyect.serviciorest.dto.RatingDTO;


public interface RatingService {
    boolean usuarioYaCalifico(Long userId, Long filmId);

    void registerRating(RatingDTO ratingDTO);

    double calcularAverage(Long filmId);

    int buscarScore (Long userId, Long filmID);
}
