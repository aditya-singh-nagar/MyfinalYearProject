package mpack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mpack.dto.ProductAddDTO;
import mpack.services.MyProductsServiceImplClass;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MyProductController {
	
	@Autowired
	private MyProductsServiceImplClass  service;
	
	@PostMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addProducts( @Valid @RequestBody  ProductAddDTO prod){
		
	return new ResponseEntity<>(service.addProducts(prod), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/allProducts")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "12") int size , @RequestParam(defaultValue = "id") String sortBy , @RequestParam(defaultValue = "asc") String direction  ){
		
		return new ResponseEntity<>(service.getAllProducts(page, size, sortBy, direction), HttpStatus.OK);
	}
	
	@GetMapping("/allProductsTwo")
	public ResponseEntity<?> getAllProductsTwo (Pageable pageable){
		
		return new ResponseEntity<>(service.getAllProductsPagination(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/allProductsLast")
	public ResponseEntity<?> getallproductsFilterSorting ( @RequestParam(required = false) String category  , Pageable pageable){
		
		if(category != null ) {
			
			return new  ResponseEntity<>(service.getAllProductsLast(category, pageable), HttpStatus.OK);
		}
		else {
			return ResponseEntity.ok(service.getAllProductsPagination(pageable));
		}
		
		
	}
	
	@GetMapping("/products/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getOneProduct(@PathVariable int id){
		return new ResponseEntity<>(service.getOneProducts(id), HttpStatus.OK);
	}
	
	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateProduct(@PathVariable int id , @RequestBody ProductAddDTO  prod){
		return new ResponseEntity<>(service.updateProducts(prod, id), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProduct (@PathVariable int id ){
		
		return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
