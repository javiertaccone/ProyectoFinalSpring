package org.proyect.appweb.services.implementations;

import org.proyect.appweb.domain.Artist;
import org.proyect.appweb.domain.TypeArtist;
import org.proyect.appweb.dto.ArtistDTO;
import org.proyect.appweb.repositories.ArtistDAO;
import org.proyect.appweb.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDAO artistDAO;

    @Override
    public void registrarArtista (ArtistDTO artistDTO){
        if (artistDTO.getName() == null || artistDTO.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (artistDTO.getSurname() == null || artistDTO.getSurname().isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (artistDTO.getTypeArtist() == null) {
            throw new IllegalArgumentException("Tienes que escoger qué tipo de artista es");
        }

        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        artist.setSurname(artistDTO.getSurname());
        artist.setTypeArtist(artistDTO.getTypeArtist());

        artistDAO.save(artist);
    }

    @Override
    public List<Artist> findAllByTypeArtist(TypeArtist typeArtist) {
        return artistDAO.findAllByTypeArtist(typeArtist);
    }

    @Override
    public Artist findById(Long id) {
        return artistDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid artist ID: " + id));
    }

    @Override
    public List<Artist> findAllByIds(List<Long> ids) {
        return artistDAO.findAllById(ids);
    }

}
