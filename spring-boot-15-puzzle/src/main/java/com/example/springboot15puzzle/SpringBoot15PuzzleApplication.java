package com.example.springboot15puzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.springboot15puzzle")

public class SpringBoot15PuzzleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot15PuzzleApplication.class, args);
	}

}
