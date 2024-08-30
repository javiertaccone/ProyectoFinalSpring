package org.proyect.appweb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.proyect.appweb.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class UserLoginDTO {


    private String identificador;

    private String password;

    private Rol rol;
}
