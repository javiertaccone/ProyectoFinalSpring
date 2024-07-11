package org.proyect.appweb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Films")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "releaseYear")
    private Integer releaseYear;

    @Column (name = "poster")
    private String poster;
}



