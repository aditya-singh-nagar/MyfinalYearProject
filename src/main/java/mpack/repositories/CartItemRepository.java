package mpack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mpack.entities.CartItem;
import mpack.entities.MyProducts;
import mpack.entities.MyUser;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	public Optional<CartItem> findByUserAndProducts(MyUser user , MyProducts products); 
	
	public List<CartItem> findByUser (MyUser user);

}
