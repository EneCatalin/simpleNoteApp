package com.example.simpleNoteApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SimpleNoteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleNoteAppApplication.class, args);
	}

	//TODO: ADD NOTES
	//TODO: NOTES ARE A PART OF BOARDS BOARDS CAN CONTAIN MULTIPLE USERS (figure out how to invite/kick the other users
	// later and give RW or R only access
}
