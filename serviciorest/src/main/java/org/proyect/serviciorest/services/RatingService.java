package org.proyect.serviciorest.services;

import org.proyect.serviciorest.entities.Rating;

public interface RatingService {

    Rating crearValoracion (Rating rating);

    Rating verValoracionDeUsuario (Rating rating);

    Rating verMediaFilm (Rating rating);

    Rating usarRestSinAcceso (Rating rating);
}
