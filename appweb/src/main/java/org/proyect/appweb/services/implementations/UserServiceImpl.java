package org.proyect.appweb.services.implementations;

import org.proyect.appweb.domain.Rol;
import org.proyect.appweb.domain.User;
import org.proyect.appweb.dto.UserLoginDTO;
import org.proyect.appweb.dto.UserRegisterDTO;
import org.proyect.appweb.repositories.RolDAO;
import org.proyect.appweb.repositories.UserDAO;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;*/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RolDAO rolDAO;

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
        user.setPassword(userRegisterDTO.getPassword());
        user.setBirthDate(userRegisterDTO.getBirthDate());
        user.setRol(rol);

        userDAO.save(user);
    }

    @Override
    public boolean validarDatosAcceso(UserLoginDTO userLoginDTO) {
    String identificador = userLoginDTO.getIdentificador();
    User user = null;

    if (userDAO.existsByUsername((identificador))){
        user = userDAO.findByUsername(identificador);
    } else if (userDAO.existsByEmail(identificador)){
        user = userDAO.findByEmail(identificador);
    }

    if (user == null){
        throw new IllegalArgumentException("Usuario no encontrado");
    }

    if (user.getPassword().equals(userLoginDTO.getPassword())){
        return true;
    } else {
        throw new IllegalArgumentException("Contraseña invalida");
    }
    }

/*    @Override
    public Long getAuthenticatedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userDAO.findByUsername(username);
            if (user != null) {
                return user.getId();
            }
        }
        return null;
    }*/
}
