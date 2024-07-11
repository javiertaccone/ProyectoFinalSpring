package org.proyect.appweb.repositories;

import org.proyect.appweb.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository <Film, String> {
}
