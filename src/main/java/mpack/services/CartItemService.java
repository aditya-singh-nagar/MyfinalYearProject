package mpack.services;

import java.util.List;

import mpack.entities.CartItem;

public interface CartItemService {
	
	public CartItem  addProductToCart (String userEmail , int productId , int quantity ); 
	public  CartItem  removeProductToCart (int cartItemId);
	public  List<CartItem> getAllCartproduct ();
	public  CartItem  updateCartProduct (int cartItemId , int quantity );
	public List<CartItem> getAllproductsForOneUser (String email);

}
