package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiEmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiEmployeesApplication.class, args);
	}

}
