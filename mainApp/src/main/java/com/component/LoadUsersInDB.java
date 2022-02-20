package com.component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.count() > 0) {
			return;
		}
		
		User user1 = new User("Clark", UUID.randomUUID().toString(), "Clark Matthew", "Kent", 30, "Norway");
		User user2 = new User("jenny", UUID.randomUUID().toString(), "Jennifer", "Swain", 24, "Ireland");
		User user3 = new User("Brucy", UUID.randomUUID().toString(), "Bruce", "Wayne", 34, "Scotland");
		User user4 = new User("Captain", UUID.randomUUID().toString(), "Captain", "America", 41, "London");
		User user5 = new User("Deveoper39", UUID.randomUUID().toString(), "Deveoper", "39", 23, "France");
		User user6 = new User("SS", UUID.randomUUID().toString(), "Spongebob", "Squarepants", 29, "London");
		User user7 = new User("DeveloperM", UUID.randomUUID().toString(), "Developer", "M", 25, "London");
		User user8 = new User("GabyW", UUID.randomUUID().toString(), "Gaby", "williams", 18, "London");
		User user9 = new User("KKF", UUID.randomUUID().toString(), "KK", "Finn", 36, "France");
		User user10 = new User("MariaC", UUID.randomUUID().toString(), "Maria", "Conhagen", 24, "France");

		List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

		usersList = usersList.stream().map(user -> {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return user;
		}).collect(Collectors.toList());
		
		userRepository.saveAll(usersList);

	}

}
