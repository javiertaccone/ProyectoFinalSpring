package org.proyect.procesobatch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table (name = "Film_Exports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "exportedAt")
    private LocalDate exportedAt;

    @Column (name = "filmid")
    private Long filmid;

    @Column (name = "jobid")
    private Integer jobid;

}
