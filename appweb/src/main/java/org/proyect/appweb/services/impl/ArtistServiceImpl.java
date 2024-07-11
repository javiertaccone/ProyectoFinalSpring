package org.proyect.appweb.services.impl;

import org.proyect.appweb.entities.Artist;
import org.proyect.appweb.repositories.ArtistRepository;
import org.proyect.appweb.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist crearArtista(Artist artist){
        return artist;
    }


}
