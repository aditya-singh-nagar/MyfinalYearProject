package mpack.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mpack.configurations.SecurityConfiguration;
import mpack.dto.LoginDTO;
import mpack.dto.RegisterDTO;
import mpack.dto.RequestMapper;
import mpack.dto.ResponseDTO;
import mpack.entities.MyUser;
import mpack.repositories.MyUserRepository;
import mpack.utilities.JwtUtility;

@Service
public class MyUserServiceImplClass implements MyUserService {
	
	@Autowired
	private MyUserRepository repo ;
	
	@Autowired
	private RequestMapper mapper ;
	
	@Autowired 
	private SecurityConfiguration config ;
	
	@Autowired
	private JwtUtility utility;

	@Override
	public ResponseDTO addUser(RegisterDTO reg) {
		
		MyUser user = mapper.registerDtoToUserEntity(reg);
		user.setPassword(config.getEncoder().encode(user.getPassword()));
		MyUser user2 = repo.save(user);
		ResponseDTO res = mapper.userEntityToResponseDto(user2);
		
		return res;
	}

	@Override
	public String getUser(LoginDTO log) {
		
		MyUser user = repo.findByEmail(log.getEmail());
		
		if (user != null && config.getEncoder().matches(log.getPassword(), user.getPassword())) {
	
			String role = user.getRole().replace("ROLE_", "");
			
			return utility.generateToken(user.getEmail(), role , user.getUsername()) ;
			
		}else {
			throw new RuntimeException("user not found");
		}
		
		
	}

	@Override
	public List<MyUser> getAllUser() {
	
		return repo.findAll();
	}

}
