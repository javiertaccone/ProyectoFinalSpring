package org.proyect.serviciorest.repositories;

import org.proyect.serviciorest.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingDAO extends JpaRepository <Rating, Long> {

    boolean existsByUserIdAndFilmId(Long userId, Long filmId);

    List<Rating> findByFilmId(Long filmID);

    Rating findByUserIdAndFilmId(Long userId, Long filmID);

}
