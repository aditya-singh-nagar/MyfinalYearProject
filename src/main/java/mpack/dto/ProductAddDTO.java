package mpack.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductAddDTO {
	
	@NotEmpty(message = "name must not empty")
	@Size(min=4 , max=30 , message = "name must be greater then 4 and less than 20")
	private String name ;
	
	@NotEmpty(message = "description must not empty")
	@Size(min = 10 , max=100 , message = "description must be greater then 20 and less than 100")
	private String description ;
	
	
	@NotEmpty(message = "category must not empty")
	@Size(min=4 , max=30 , message = "category must be greater then 4 and less than 20")
	private String category ;
	
	@NotNull(message = "price is required")
	@Positive(message = "price must be positive")
	private  double  price ;
	
	@NotEmpty(message = "image must not empty")
	@Size(max=200 , message = "image must be less than 50 words")
	private String  image ;
	
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
	private int  stock ;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	

}
