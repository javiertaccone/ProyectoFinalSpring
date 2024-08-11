package org.proyect.appweb.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.proyect.appweb.domain.TypeArtist;

@Data
public class ArtistDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Enumerated(EnumType.STRING)
    private TypeArtist typeArtist;

}
