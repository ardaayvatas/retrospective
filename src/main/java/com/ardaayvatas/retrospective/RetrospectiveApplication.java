package com.ardaayvatas.retrospective;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RetrospectiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrospectiveApplication.class, args);
	}

}