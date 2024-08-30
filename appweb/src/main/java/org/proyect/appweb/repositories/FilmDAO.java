package org.proyect.appweb.repositories;

import org.proyect.appweb.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmDAO extends JpaRepository <Film, String> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    Optional<Film> findById(Long id);
}
