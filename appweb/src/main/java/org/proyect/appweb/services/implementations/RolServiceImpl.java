package org.proyect.appweb.services.implementations;

import org.proyect.appweb.repositories.UserDAO;
import org.proyect.appweb.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public String getRolByUsername(String username){
        return userDAO.findByUsername(username).getRol().getRolType();
    }
}
