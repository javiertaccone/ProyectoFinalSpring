package org.proyect.appweb.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RolDTO {

    @Column
    private String rolType;

}
