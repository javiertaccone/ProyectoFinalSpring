package org.proyect.appweb.services;

import org.proyect.appweb.domain.Film;
import org.proyect.appweb.domain.TypeArtist;
import org.proyect.appweb.dto.ArtistDTO;
import org.proyect.appweb.dto.FilmDTO;
import org.proyect.appweb.dto.SearchDTO;

import java.util.List;

public interface FilmService {

    void registrarFilm (FilmDTO filmDTO);

    List<Film> defaultCharge ();
    List<Film> buscarFilm (String title);

    Film findByID(Long id);
}
