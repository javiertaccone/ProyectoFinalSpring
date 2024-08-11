package org.proyect.appweb.services;

import org.proyect.appweb.domain.Artist;
import org.proyect.appweb.domain.TypeArtist;
import org.proyect.appweb.dto.ArtistDTO;

import java.util.List;

public interface ArtistService {

    void registrarArtista(ArtistDTO artistDTO);

    List<Artist> findAllByTypeArtist(TypeArtist typeArtist);

    Artist findById(Long id);

    List<Artist> findAllByIds(List<Long> ids);
}
