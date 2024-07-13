package org.proyect.procesobatch.repositories;

import org.proyect.procesobatch.domain.FilmExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmExportDAO extends JpaRepository<FilmExport, Long> {
}
