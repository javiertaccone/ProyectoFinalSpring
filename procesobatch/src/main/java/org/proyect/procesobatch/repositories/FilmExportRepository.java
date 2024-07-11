package org.proyect.procesobatch.repositories;

import org.proyect.procesobatch.entities.FilmExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmExportRepository extends JpaRepository<FilmExport, Long> {
}
