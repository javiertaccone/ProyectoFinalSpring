package org.proyect.appweb.services;

import org.proyect.appweb.domain.User;
import org.proyect.appweb.dto.UserLoginDTO;
import org.proyect.appweb.dto.UserRegisterDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    Long getAuthenticatedUserId();
}
