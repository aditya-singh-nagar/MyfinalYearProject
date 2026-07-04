package mpack.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import mpack.entities.MyUser;

@Component
public class RequestMapper {
	
	
	public MyUser  registerDtoToUserEntity(RegisterDTO reg) {
		
		MyUser user = new MyUser();
		user.setUsername(reg.getUsername());
		user.setEmail(reg.getEmail());
		user.setPassword(reg.getPassword());
		user.setRole(reg.getRole());
		
		return user ;
		
	}
	

	public ResponseDTO  userEntityToResponseDto (MyUser user) {
		
		ResponseDTO res = new ResponseDTO();
		res.setId(user.getId());
		res.setEmail(user.getEmail());
		res.setUsername(user.getUsername());
		res.setRole(user.getRole());
		
		return res ;
	}

}
