package org.proyect.serviciorest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "createdAt")
    private LocalDate createdAt;

    @Column (name = "filmid")
    private Long filmid;

    @Column (name = "userid")
    private Long userid;

    @Column (name = "score")
    private Integer score;
}
