package com.rokin.oauth2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rokin.oauth2.user.model.User;
import com.rokin.oauth2.user.repository.UserRepository;

@SpringBootApplication
public class SpringOauth2Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringOauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.userRepository.findByUsername("rokin") == null) {
			User user = new User("Rokin Maharjan", "rokin", passwordEncoder.encode("rokin123"), Arrays.asList("ADMIN"));

			this.userRepository.save(user);
		}
	}

}
