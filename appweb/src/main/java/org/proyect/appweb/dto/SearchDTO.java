package org.proyect.appweb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchDTO {

    @NotBlank
    private String searchTitle;
}
