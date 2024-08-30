package org.proyect.appweb.dto;

import lombok.Data;

import java.util.List;

@Data
public class EditFilmDTO {

    private Long id;

    private String title;

    private Integer releaseYear;

    private Long directorID;

    private String poster;

    private List<Long> actorsID;
}
