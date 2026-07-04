package mpack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue
	private int id  ; 
	
	@ManyToOne
	@JoinColumn
	private MyUser user ;
	
	@ManyToOne
	@JoinColumn
	private MyProducts products ;
	
	private int quantity ;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public MyProducts getProducts() {
		return products;
	}

	public void setProducts(MyProducts products) {
		this.products = products;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	} 
	
	
	

}
