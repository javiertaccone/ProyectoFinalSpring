package org.proyect.appweb.repositories;

import org.proyect.appweb.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmDAO extends JpaRepository <Film, String> {
}
