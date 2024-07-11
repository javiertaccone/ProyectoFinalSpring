package org.proyect.appweb.services;

import org.proyect.appweb.entities.User;

public interface UserService {

    User crearUser (User user);

    User validarAcceso (User user);

    User logout (User user);

}
