package mpack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mpack.dto.LoginDTO;
import mpack.dto.RegisterDTO;
import mpack.dto.ResponseDTO;
import mpack.entities.MyUser;
import mpack.services.MyUserServiceImplClass;

@RestController
@CrossOrigin(origins = "*")

public class MyUserController {
	
	@Autowired
	private MyUserServiceImplClass service ;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody RegisterDTO reg){
		
		return new ResponseEntity<>(service.addUser(reg), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getuser( @Valid @RequestBody LoginDTO log){
		
		return new ResponseEntity<>(service.getUser(log), HttpStatus.OK);
	}
	
	@GetMapping("/")

	
	public String getHomePage() {
		return "this is a home page" ;
	}
	
	@GetMapping("/allUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUser(){
		
		return new ResponseEntity<List<MyUser>>(service.getAllUser(), HttpStatus.OK);
	}
	
	

}
