package org.proyect.procesobatch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "Film_Exports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private Long filmId;
    private LocalDateTime exportedAt;

    public FilmExport(long l, Long id, LocalDateTime now) {
    }
}
