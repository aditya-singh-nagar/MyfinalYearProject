package mpack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mpack.services.OrderItemServiceImplClass;

@RestController
public class OrderItemController {
	
	@Autowired
	private OrderItemServiceImplClass service ; 
	
	@PostMapping("/orderitem")
	@PreAuthorize("hasAnyRole('ADMIN','ROLE')")
	public ResponseEntity<?> AddOrderAndOrderItem (Authentication authentication){
		
		return new ResponseEntity<>(service.addOrderItem(authentication.getName()), HttpStatus.CREATED);
	}

}
