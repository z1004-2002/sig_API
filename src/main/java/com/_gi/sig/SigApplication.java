package com._gi.sig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com._gi.sig.models.Role;
import com._gi.sig.models.User;
import com._gi.sig.repository.UserRepository;

@SpringBootApplication
public class SigApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SigApplication.class, args);
	}

	@Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
			List<User> user = userRepository.findByLogin("admin");
			if(user.size()==0){
				userRepository.save( 
					User.builder()
					.login("admin")
					.password("0000")
					.role(Role.ADMIN)
					.phone("00000000")
					.build()
				);
			}
        };
    }

}
