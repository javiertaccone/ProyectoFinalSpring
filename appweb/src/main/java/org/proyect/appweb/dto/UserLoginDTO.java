package org.proyect.appweb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDTO {


    private String identificador;

    @NotBlank
    @Size(max = 20)
    private String password;
}
