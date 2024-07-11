package org.proyect.appweb.repositories;

import org.proyect.appweb.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository <Artist,Long> {
}
