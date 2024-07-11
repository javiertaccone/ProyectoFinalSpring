package org.proyect.serviciorest.services;

import org.proyect.serviciorest.entities.Rating;
import org.proyect.serviciorest.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating crearValoracion (Rating rating){
        return rating;
    }

    @Override
    public Rating verValoracionDeUsuario (Rating rating){
        return rating;
    }

    @Override
    public Rating verMediaFilm (Rating rating){
        return rating;
    }

    @Override
    public Rating usarRestSinAcceso (Rating rating){
        return rating;
    }
}
