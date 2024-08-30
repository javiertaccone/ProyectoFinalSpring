package org.proyect.appweb.services;
import org.proyect.appweb.dto.UserRegisterDTO;
import org.proyect.appweb.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    Long getAuthenticatedUserId();

    String getRoltyperUserById(Long id);
}
