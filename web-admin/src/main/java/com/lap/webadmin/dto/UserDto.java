package com.lap.webadmin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

	@NotEmpty(message = "User Name should not be empty")
	private String userName;

	@NotEmpty(message = "Password should not be empty")
	private String password;

	@NotEmpty(message = "Name should not be empty")
	private String fullName;

	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	
}
