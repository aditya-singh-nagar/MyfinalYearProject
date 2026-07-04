package mpack.services;



import java.util.List;

import mpack.dto.LoginDTO;
import mpack.dto.RegisterDTO;
import mpack.dto.ResponseDTO;
import mpack.entities.MyUser;

public interface MyUserService {
	
	public ResponseDTO  addUser (RegisterDTO reg);
	public String getUser (LoginDTO log);
	public List<MyUser> getAllUser();
	

}
