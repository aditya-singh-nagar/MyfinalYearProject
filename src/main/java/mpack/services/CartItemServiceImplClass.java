package mpack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mpack.entities.CartItem;
import mpack.entities.MyProducts;
import mpack.entities.MyUser;
import mpack.repositories.CartItemRepository;
import mpack.repositories.MyProductsRepository;
import mpack.repositories.MyUserRepository;

@Service
public class CartItemServiceImplClass implements CartItemService{
	
	@Autowired
	private MyProductsRepository productRepo ;
	
	@Autowired
	private MyUserRepository userRepo ;
	
	@Autowired
	private CartItemRepository cartRepo ;

	@Override
	@Transactional
	public CartItem addProductToCart(String userEmail, int productId, int quantity) {
		
		MyUser user = userRepo.findByEmail(userEmail);
		MyProducts product = productRepo.findById(productId).get();
		
		Optional<CartItem> opt = cartRepo.findByUserAndProducts(user, product);
		
		if(opt.isPresent()) {
			
			CartItem item = opt.get();
			
			item.setQuantity(item.getQuantity() + quantity);
			
			return cartRepo.save(item);
		}
		
		else {
			CartItem item = new CartItem();
			item.setProducts(product);
			item.setQuantity(quantity);
			item.setUser(user);
			
			
			return cartRepo.save(item);
		}
		


		
	
		
	}

	@Override
	@Transactional
	public CartItem removeProductToCart(int cartItemId) {
		
		Optional<CartItem> opt = cartRepo.findById(cartItemId);
		
		cartRepo.deleteById(cartItemId);
		
		return opt.get();
	}

	@Override
	public List<CartItem> getAllCartproduct() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

	@Override
	@Transactional
	public CartItem updateCartProduct(int cartItemId, int quantity) {
		
		Optional<CartItem> opt = cartRepo.findById(cartItemId);
		
		MyProducts prod = opt.get().getProducts();
		
		if(prod.getStock() > quantity) {
			CartItem item = opt.get();
			item.setQuantity(quantity);
			
			return cartRepo.save(item);
		}else {
			throw new RuntimeException("quantity must be less than stock");
		}
		
		


	}

	@Override
	public List<CartItem> getAllproductsForOneUser(String email) {
		
		MyUser user = userRepo.findByEmail(email);
		
		return cartRepo.findByUser(user);
	}


  
	


}
