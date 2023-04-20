package com.example.userservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.vo.Greeting;

@RestController
@RequestMapping("/")
public class UserController {

	private final Environment env;
	private final Greeting greeting;

	public UserController(Environment env, Greeting greeting) {
		this.env = env;
		this.greeting = greeting;
	}

	@GetMapping("/health_check")
	public String status(){
		return "It's Working In User Service";
	}


	@GetMapping("/welcome")
	public String welcome(){
		// return env.getProperty("greeting.message");
		return greeting.getMessage();
	}
}
