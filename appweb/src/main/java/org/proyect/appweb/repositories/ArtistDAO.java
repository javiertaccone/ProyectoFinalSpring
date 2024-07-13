package org.proyect.appweb.repositories;

import org.proyect.appweb.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistDAO extends JpaRepository <Artist,Long> {
}
