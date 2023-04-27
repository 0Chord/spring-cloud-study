package com.example.userservice.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestLogin {
	@NotNull(message = "Email can not be null")
	@Size(min = 2, message = "Email not be less than two characters")
	@Email
	private String email;

	@NotNull(message = "Password can not be null")
	@Size(min = 8, message = "Password must be equals or grater than 8 characters")
	private String password;
}
