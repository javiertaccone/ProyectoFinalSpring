package org.proyect.appweb.repositories;

import org.proyect.appweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository <User,Long> {

}
