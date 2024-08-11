package org.proyect.appweb.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class UserRegisterDTO {

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String surname;

    @NotBlank
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
}
