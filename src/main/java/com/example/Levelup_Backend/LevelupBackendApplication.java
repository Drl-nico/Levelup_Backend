package com.example.Levelup_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class LevelupBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevelupBackendApplication.class, args);
	}

}
