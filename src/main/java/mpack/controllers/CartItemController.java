package mpack.controllers;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mpack.dto.AddCartItemDto;
import mpack.entities.CartItem;
import mpack.services.CartItemServiceImplClass;

@RestController
public class CartItemController {
	
	@Autowired
	private CartItemServiceImplClass service ;
	
	@PostMapping("/cartitem")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> addproduct (@RequestBody AddCartItemDto dto , Authentication authentication ){
		
		return new ResponseEntity<>(service.addProductToCart(authentication.getName(), dto.getProductId(), dto.getQuantity()), HttpStatus.CREATED);

	}
	
	@GetMapping("/cartitem")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getProduct (){
		
		return new ResponseEntity<>(service.getAllCartproduct(), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/cartitem/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> deleteProduct (@PathVariable int id ){
		
		return new ResponseEntity<>(service.removeProductToCart(id), HttpStatus.OK);
	}
	
	
	@PatchMapping("/cartitem/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> updateProduct (@PathVariable int id , @RequestBody AddCartItemDto dto){
		return new ResponseEntity<>(service.updateCartProduct(id, dto.getQuantity()),HttpStatus.OK );
	}
	
	@GetMapping("/cartitemOne")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getProductForOneUser(Authentication authentication) {
		
		return new ResponseEntity<>(service.getAllproductsForOneUser(authentication.getName()), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	

}
