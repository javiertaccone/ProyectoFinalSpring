package org.proyect.appweb.services.implementations;

import org.proyect.appweb.domain.Rol;
import org.proyect.appweb.domain.User;
import org.proyect.appweb.dto.UserRegisterDTO;
import org.proyect.appweb.repositories.RolDAO;
import org.proyect.appweb.repositories.UserDAO;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (userDAO.existsByUsername(userRegisterDTO.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está tomado");
        }
        if (userDAO.existsByEmail(userRegisterDTO.getEmail())) {
            throw new IllegalArgumentException("Ya hay un usuario con ese correo");
        }

        Rol rol = rolDAO.findByRolType("user");
        if (rol == null) {
            rol = new Rol();
            rol.setRolType("user");
            rolDAO.save(rol);
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setSurname(userRegisterDTO.getSurname());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setBirthDate(userRegisterDTO.getBirthDate());
        user.setRol(rol);

        userDAO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRol().getRolType())
                .build();
    }

    @Override
    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String username = userDetails.getUsername();
            User user = userDAO.findByUsername(username);
            return user.getId();
    }

    @Override
    public String getRoltyperUserById(Long id){
        Rol user = userDAO.findById(id).get().getRol();
        String rol = user.getRolType();
        return rol;
    }
}
