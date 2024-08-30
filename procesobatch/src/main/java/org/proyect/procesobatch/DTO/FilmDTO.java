package org.proyect.procesobatch.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {

    private Long id;
    private String title;
    private int releaseYear;

}
