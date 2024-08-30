package org.proyect.appweb.services.implementations;

import org.proyect.appweb.domain.Artist;
import org.proyect.appweb.domain.Film;
import org.proyect.appweb.dto.EditFilmDTO;
import org.proyect.appweb.dto.FilmDTO;
import org.proyect.appweb.repositories.FilmDAO;
import org.proyect.appweb.services.ArtistService;
import org.proyect.appweb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {


    @Autowired
    private FilmDAO filmDAO;

    @Autowired
    private ArtistService artistService;


    @Override
    public void registrarFilm(FilmDTO filmDTO){
        if (filmDTO.getTitle() == null || filmDTO.getTitle().isBlank()){
            throw new IllegalArgumentException("El titulo no puede estar vacío");
        }
        if (filmDTO.getReleaseYear() == null){
            throw new IllegalArgumentException("No puede estar vacía la fecha");
        }

        int currentYear = Year.now().getValue();
        int releaseYear = filmDTO.getReleaseYear();

        if (releaseYear > currentYear) {
            throw new IllegalArgumentException("No puede ser mayor a la actual");
        }
        if (releaseYear <= 1894) {
            throw new IllegalArgumentException("No puede ser menor al 1894");
        }
        if (filmDTO.getPoster() == null || filmDTO.getTitle().isBlank()){
            throw new IllegalArgumentException("El poster no puede estar vacío");
        }

        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setPoster(filmDTO.getPoster());

        Artist director = artistService.findById(filmDTO.getDirectorID());
        film.setDirector(director);

        List<Artist> actors = artistService.findAllByIds(filmDTO.getActorsID());
        film.setActors(actors);

        filmDAO.save(film);
    }

    @Override
    public void editarFilm(EditFilmDTO editFilmDTO) {
        if (editFilmDTO.getId() == null) {
            throw new IllegalArgumentException("El ID del film no puede estar vacío");
        }

        Optional<Film> optionalFilm = filmDAO.findById(editFilmDTO.getId());
        if (optionalFilm.isEmpty()) {
            throw new IllegalArgumentException("El Film con el ID proporcionado no existe");
        }

        Film film = optionalFilm.get();

        if (editFilmDTO.getTitle() == null || editFilmDTO.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (editFilmDTO.getReleaseYear() == null) {
            throw new IllegalArgumentException("La fecha de lanzamiento no puede estar vacía");
        }

        int currentYear = Year.now().getValue();
        int releaseYear = editFilmDTO.getReleaseYear();

        if (releaseYear > currentYear) {
            throw new IllegalArgumentException("La fecha de lanzamiento no puede ser mayor al año actual");
        }
        if (releaseYear <= 1894) {
            throw new IllegalArgumentException("La fecha de lanzamiento no puede ser menor a 1894");
        }
        if (editFilmDTO.getPoster() == null || editFilmDTO.getPoster().isBlank()) {
            throw new IllegalArgumentException("El póster no puede estar vacío");
        }

        film.setTitle(editFilmDTO.getTitle());
        film.setReleaseYear(editFilmDTO.getReleaseYear());
        film.setPoster(editFilmDTO.getPoster());

        Artist director = artistService.findById(editFilmDTO.getDirectorID());
        film.setDirector(director);

        List<Artist> actors = artistService.findAllByIds(editFilmDTO.getActorsID());
        film.setActors(actors);

        filmDAO.save(film);
    }



    @Override
    public List<Film> buscarFilm (String title){
        return filmDAO.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Film> defaultCharge (){
        return filmDAO.findAll();
    }

    @Override
    public Film findByID(Long id) {
        return filmDAO.findById(String.valueOf(id)).orElse(null);
    }


}
