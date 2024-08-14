package org.proyect.serviciorest.services.implementations;

import org.proyect.serviciorest.domain.Rating;
import org.proyect.serviciorest.dto.RatingDTO;
import org.proyect.serviciorest.repositories.RatingDAO;
import org.proyect.serviciorest.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDAO ratingDAO;


    @Override
    public boolean usuarioYaCalifico(Long userId, Long filmId) {
        return ratingDAO.existsByUserIdAndFilmId(userId, filmId);
    }

    @Override
    public int buscarScore (Long userId, Long filmID){
        return ratingDAO.findByUserIdAndFilmId(userId, filmID).getScore();

    }

    @Override
    public void registerRating(RatingDTO ratingDTO){
        Rating rating = new Rating();
        rating.setFilmId(ratingDTO.getFilmId());
        rating.setUserId(ratingDTO.getUserId());
        rating.setScore(ratingDTO.getScore());

        ratingDAO.save(rating);
    }

    @Override
    public double calcularAverage(Long filmId) {
        List<Rating> ratingsPorFilm = ratingDAO.findByFilmId(filmId);

        if (ratingsPorFilm.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Rating rating : ratingsPorFilm) {
            sum += rating.getScore();
        }

        double average = sum / ratingsPorFilm.size();

        DecimalFormat df = new DecimalFormat("#.0");
        String averageStr = df.format(average);

        averageStr = averageStr.replace(',', '.');

        return Double.parseDouble(averageStr);
    }
}
