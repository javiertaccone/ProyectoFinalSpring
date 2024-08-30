package org.proyect.appweb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FilmDTO {

    private String title;

    private Integer releaseYear;

    private Long directorID;

    private String poster;

    private List<Long> actorsID;
}
