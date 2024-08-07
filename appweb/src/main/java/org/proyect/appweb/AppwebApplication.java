package org.proyect.appweb;

import org.proyect.appweb.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppwebApplication /*implements CommandLineRunner*/ {

	@Autowired
	public UserDAO userDAO;

	public static void main(String[] args) {
		SpringApplication.run(AppwebApplication.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(1L,"arturo","arturo","arturo","arturo","arturo","arturo",new Date(),LocalDate.now(),LocalDate.now()));
		userRepository.save(new User(2L,"luis","luis","luis","luis","luis","luis",new Date(),LocalDate.now(),LocalDate.now()));

		System.out.println("Numero de personas en la tabla" + userRepository.count());
	}*/
}
