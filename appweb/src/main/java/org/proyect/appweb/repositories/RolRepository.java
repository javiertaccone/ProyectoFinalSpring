package org.proyect.appweb.repositories;

import org.proyect.appweb.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository <Rol,Long> {
}
