package org.proyect.appweb.services;

import org.proyect.appweb.entities.Film;

public interface FilmService {

    Film crearFilm (Film film);

    Film buscarFilms (Film film);

    Film editarFilms (Film film);

    Film verDetalle (Film film);

    Film puntuarFilm (Film film);

    Film verPuntuacionEnviada (Film film);

    Film verPuntuacionMedia (Film film);
}
