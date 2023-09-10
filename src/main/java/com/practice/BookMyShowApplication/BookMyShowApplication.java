package com.practice.BookMyShowApplication;

import com.practice.BookMyShowApplication.controllers.UserController;
import com.practice.BookMyShowApplication.dtos.SignUpUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {


	@Autowired
	private UserController userController;

	@Override
	public void run(String... args) throws Exception {
		SignUpUserRequestDto requestDto = new SignUpUserRequestDto();
		requestDto.setName("Neha");
		requestDto.setEmail("Neha@xyz");
		requestDto.setPassword("password123");
		userController.signUp(requestDto);
	}

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

}
