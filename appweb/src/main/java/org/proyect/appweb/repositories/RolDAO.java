package org.proyect.appweb.repositories;

import org.proyect.appweb.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolDAO extends JpaRepository <Rol,Long> {

    Rol findByRolType(String rol);
}
