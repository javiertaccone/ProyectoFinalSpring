package org.proyect.procesobatch.services.impl;

import org.proyect.procesobatch.entities.FilmExport;
import org.proyect.procesobatch.repositories.FilmExportRepository;
import org.proyect.procesobatch.services.FilmExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmExportServiceImpl implements FilmExportService {

    @Autowired
    private FilmExportRepository filmExportRepository;

    @Override
    public FilmExport selectFilmsNoExport (FilmExport filmExport){
        return filmExport;
    }

    @Override
    public FilmExport generarCSV (FilmExport filmExport){
        return filmExport;
    }

    @Override
    public FilmExport notifyFinish (FilmExport filmExport){
        return filmExport;
    }

}
