package org.proyect.appweb.repositories;

import org.proyect.appweb.domain.Artist;
import org.proyect.appweb.domain.TypeArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistDAO extends JpaRepository <Artist,Long> {

    List<Artist> findAllByTypeArtist(TypeArtist typeArtist);
}
