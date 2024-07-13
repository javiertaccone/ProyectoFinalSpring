package org.proyect.appweb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "username")
    private String username;

    @Column (name = "email")
    private String email;

    @Column (name = "surname")
    private String surname;

    @Column (name = "password")
    private String password;

    @Column (name = "rol usuario")
    private String rol_usuario;

    @Column (name = "birthDate")
    private Date birthDate;

    @Column (name = "lastLoginAt")
    private LocalDate lastLoginAt;

    @Column (name = "createdAt")
    private LocalDate createdAt;


}
