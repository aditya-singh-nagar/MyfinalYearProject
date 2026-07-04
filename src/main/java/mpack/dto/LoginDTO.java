package mpack.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class LoginDTO {
	
	@NotEmpty(message = "email must not empty")
	@Email(message = "email must be in valid email format")
//	@Pattern(regexp = "$[a-zA-Z0-9\\-\\.+_]+@[a-z]+\\.[a-z]+^" ,message = "email must be in valid email format ")
	private String email ;
	
	
	@NotEmpty(message = "password must not empty")
	@Size(min = 4 , max = 15 , message = "password must be greater than 5 and less than 12 and does not contain any special character")
//	@Pattern(regexp = "$[a-zA-Z0-9\\.\\-+_@#$!%&*]{5,12}^" , message = "password must be greater than 5 and less than 12 and should contain any special character")
	private String password ;
	
	
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
	
	

}
