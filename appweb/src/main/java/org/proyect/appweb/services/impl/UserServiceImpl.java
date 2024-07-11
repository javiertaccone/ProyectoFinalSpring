package org.proyect.appweb.services.impl;

import org.proyect.appweb.entities.User;
import org.proyect.appweb.repositories.UserRepository;
import org.proyect.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User crearUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User validarAcceso(User user){
        return user;
    }

    @Override
    public User logout(User user){
        return user;
    }

}
