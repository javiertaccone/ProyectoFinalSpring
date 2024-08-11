package org.proyect.appweb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Films")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "releaseYear", nullable = false)
    private Integer releaseYear;

    @NotBlank
    @Column(name = "poster", nullable = false)
    private String poster;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Artist director;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "film_actors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> actors;
}

