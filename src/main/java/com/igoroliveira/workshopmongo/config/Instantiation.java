package com.igoroliveira.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.igoroliveira.workshopmongo.domain.Post;
import com.igoroliveira.workshopmongo.domain.User;
import com.igoroliveira.workshopmongo.dto.AuthorDTO;
import com.igoroliveira.workshopmongo.dto.CommentDTO;
import com.igoroliveira.workshopmongo.repository.PostRepository;
import com.igoroliveira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User messi = new User(null, "Messi", "messi@goat.com");
		User federer = new User(null, "Roger Federer", "roger@federer.com");
		User vinijr = new User(null, "Vini jr", "vini@jr.com");
		
		userRepository.saveAll(Arrays.asList(messi, federer, vinijr));
		
		Post post1 = new Post(null, sdf.parse("24/05/2023"), "Man city will win the ucl",
				"Man city is the clear favorite and this time they won't fumble the cup", new AuthorDTO(messi));
		Post post2 = new Post(null, sdf.parse("23/05/2023"), "I want to leave PSG",
				"can't take this team anymore, I want to leave ASAP!", new AuthorDTO(messi));
		
		CommentDTO c1 = new CommentDTO("Despite being the underdogs, i think Inter will take the throphy!", 
				sdf.parse("25/05/2023"), new AuthorDTO(vinijr));
		CommentDTO c2 = new CommentDTO("They have everything to win!", 
				sdf.parse("24/05/2023"), new AuthorDTO(federer));
		CommentDTO c3 = new CommentDTO("If you go back to barça i'll join you", 
				sdf.parse("23/05/2023"), new AuthorDTO(vinijr));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3)); 
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		messi.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(messi);
		
	}

}
