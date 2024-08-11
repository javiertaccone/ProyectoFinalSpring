package org.proyect.appweb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FilmDTO {

    @NotBlank
    private String title;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private Long directorID;

    @NotBlank
    private String poster;

    @NotNull
    private List<Long> actorsID;
}
