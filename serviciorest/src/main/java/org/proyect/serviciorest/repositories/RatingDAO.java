package org.proyect.serviciorest.repositories;

import org.proyect.serviciorest.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingDAO extends JpaRepository <Rating,Long> {
}
