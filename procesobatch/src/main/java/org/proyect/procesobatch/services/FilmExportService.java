package org.proyect.procesobatch.services;

import org.proyect.procesobatch.entities.FilmExport;

public interface FilmExportService {

    FilmExport selectFilmsNoExport (FilmExport filmExport);

    FilmExport generarCSV (FilmExport filmExport);

    FilmExport notifyFinish (FilmExport filmExport);

}
