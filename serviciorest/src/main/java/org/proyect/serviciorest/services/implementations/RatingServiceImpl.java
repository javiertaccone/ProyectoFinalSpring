package org.proyect.serviciorest.services.implementations;

import org.proyect.serviciorest.domain.Rating;
import org.proyect.serviciorest.dto.RatingDTO;
import org.proyect.serviciorest.repositories.RatingDAO;
import org.proyect.serviciorest.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDAO ratingDAO;

    @Override
    public void crearRating(RatingDTO ratingDTO) {
        if(RatingDAO.existsByUserIdAndFilmId(ratingDTO.getUserId(), ratingDTO.getFilmId())){
            throw new IllegalArgumentException("El usuario ya ha calificó");
        }
    }

    Rating rating = new Rating();
    rating.set

    @Override
    public boolean usuarioYaCalifico(Long userId, Long filmId) {
        return ratingDAO.existsByUserIdAndFilmId(userId, filmId);
    }

    @Override
    public List<Rating> findRatingsByFilmId(Long filmId) {
        return ratingDAO.findByFilmId(filmId);
    }

    @Override
    public double getAverageRating(Long filmId) {
        return ratingDAO.findAvgScoreByFilmId(filmId);
    }
}
