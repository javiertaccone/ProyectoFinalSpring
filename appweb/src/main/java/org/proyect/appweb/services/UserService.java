package org.proyect.appweb.services;

import org.proyect.appweb.dto.UserLoginDTO;
import org.proyect.appweb.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
    boolean validarDatosAcceso(UserLoginDTO userLoginDTO);

  /*  Long getAuthenticatedUserId();*/
}
