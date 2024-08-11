package org.proyect.serviciorest.dto;

import lombok.Data;

@Data
public class RatingDTO {

    private Long filmId;
    private Long userId;
    private int score;
}
