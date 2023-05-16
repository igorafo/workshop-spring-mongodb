package com.igoroliveira.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.igoroliveira.workshopmongo.domain.User;
import com.igoroliveira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User messi = new User(null, "Messi", "messi@goat.com");
		User federer = new User(null, "Roger Federer", "roger@federer.com");
		User vinijr = new User(null, "Vini jr", "vini@jr.com");
		
		userRepository.saveAll(Arrays.asList(messi, federer, vinijr));
	}

}
