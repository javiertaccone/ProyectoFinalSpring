package org.proyect.appweb.services.impl;

import org.proyect.appweb.entities.Film;
import org.proyect.appweb.repositories.FilmRepository;
import org.proyect.appweb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public Film crearFilm(Film film){
        return film;
    }

    @Override
    public Film buscarFilms(Film film){
        return film;
    }

    @Override
    public Film editarFilms(Film film){
        return film;
    }

    @Override
    public Film verDetalle(Film film){
        return film;
    }

    @Override
    public Film puntuarFilm(Film film){
        return film;
    }

    @Override
    public Film verPuntuacionEnviada(Film film){
        return film;
    }

    @Override
    public Film verPuntuacionMedia(Film film){
        return film;
    }
}
