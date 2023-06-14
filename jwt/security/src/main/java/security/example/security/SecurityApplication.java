package security.example.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security.example.security.model.Role;
import security.example.security.model.User;
import security.example.security.service.UserService;

import java.util.HashSet;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER","this is user"));
			userService.saveRole(new Role(null,"ROLE_ADMIN","this is user"));
			userService.saveRole(new Role(null,"ROLE_MANAGER","this is user"));

			userService.saveUser(new User("8989898989","omkar","omkar@gmail.com","pass",new HashSet<>()));
			userService.saveUser(new User("8989898989","omkar1","omkar1@gmail.com","pass",new HashSet<>()));
			userService.saveUser(new User("8989898989","omkar2","omkar2@gmail.com","pass",new HashSet<>()));

			userService.addToUser("omkar@gmail.com","ROLE_USER");
			userService.addToUser("omkar1@gmail.com","ROLE_ADMIN");
			userService.addToUser("omkar2@gmail.com","ROLE_MANAGER");



		};
	}

}
