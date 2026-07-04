package mpack.dto;

import org.springframework.stereotype.Component;

import mpack.entities.MyProducts;

@Component
public class ProductRequestMapper {
	
	public MyProducts  ProductDtoToProductEntity (ProductAddDTO prod) {
		
		MyProducts myproducts = new MyProducts();
		myproducts.setName(prod.getName());
		myproducts.setDescription(prod.getDescription());
		myproducts.setCategory(prod.getCategory());
		myproducts.setImage(prod.getImage());
		myproducts.setPrice(prod.getPrice());
		myproducts.setStock(prod.getStock());
		
		return myproducts ;
	}
	
	

}
