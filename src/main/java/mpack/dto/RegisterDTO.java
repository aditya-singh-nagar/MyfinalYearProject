package mpack.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class RegisterDTO {
	
	@NotEmpty(message = "username must not empty")
	@Size(min = 4 , max = 30 , message = "username must be greater than 5 and less than 12 and does not contain any special character")
//	@Pattern(regexp = "$[a-zA-Z0-9\\.\\-+_]{5,12}^" , message = "username must be greater than 5 and less than 12 and does not contain any special character")
	private String username ;
	
	@NotEmpty(message = "email must not empty")
	@Email(message = "email must be in valid email format")
//	@Pattern(regexp = "$[a-zA-Z0-9\\-\\.+_]+@[a-z]+\\.[a-z]+^" ,message = "email must be in valid email format 2 ")
	private String email ;
	
	@NotEmpty(message = "password must not empty")
	@Size(min = 4 , max = 25 , message = "password must be greater than 5 and less than 12 and does not contain any special character")
//	@Pattern(regexp = "$[a-zA-Z0-9\\.\\-+_@#$!%&*]{5,12}^" , message = "password must be greater than 5 and less than 12 and should contain any special character")
	private String password ;
	
	@NotEmpty(message = "role must not empty")
//	@Pattern(regexp = "$ROLE_{1}[A-Z]{4,8}^")
	private String role = "ROLE_USER" ;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
