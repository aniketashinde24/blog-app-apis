package com.example.blog.payloads;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4,message = "Username must be minimum 4 of Characters !!")
	private String  name;
	
	@Email(message = "Email Address not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 6,message = "Password is required !!")
	@Pattern(
		        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
		        message = "Password must be at least 6 characters long, contain 1 uppercase letter, 1 number, and 1 special character"
		    )
	private String password;
	
	@NotEmpty
	private String about;

}
