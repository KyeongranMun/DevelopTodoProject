package com.example.developTodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevelopTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelopTodoApplication.class, args);
	}

}
