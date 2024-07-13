package org.proyect.appweb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Artists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeArtist", nullable = false, unique = true)
    private TypeArtist typeArtist;
}
