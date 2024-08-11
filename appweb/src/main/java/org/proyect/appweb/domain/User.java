package org.proyect.appweb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column (name = "name", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column (name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column (name = "surname", nullable = false)
    private String surname;

    @NotBlank
    @Column (name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @Column (name = "birthDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column (name = "lastLoginAt")
    @CreationTimestamp private LocalDate lastLoginAt;

    @Column (name = "createdAt")
    @CreationTimestamp private LocalDate createdAt;

}
