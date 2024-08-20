package org.proyect.appweb;

import org.proyect.appweb.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppwebApplication {

	@Autowired
	public UserDAO userDAO;

	public static void main(String[] args) {
		SpringApplication.run(AppwebApplication.class, args);
	}

}
