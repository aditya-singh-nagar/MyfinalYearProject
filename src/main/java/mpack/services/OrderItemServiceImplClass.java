package mpack.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mpack.entities.CartItem;
import mpack.entities.MyOrder;
import mpack.entities.MyUser;
import mpack.entities.OrderItem;
import mpack.repositories.CartItemRepository;
import mpack.repositories.MyOrderRepository;
import mpack.repositories.MyProductsRepository;
import mpack.repositories.MyUserRepository;
import mpack.repositories.OrderItemRepository;

@Service
public class OrderItemServiceImplClass implements OrderItemService {
	
	@Autowired
	private MyUserRepository userRepo;
	@Autowired
	private MyProductsRepository productRepo ;
	@Autowired
	private CartItemRepository cartRepo;
	@Autowired
	private MyOrderRepository orderRepo;
	@Autowired
	private OrderItemRepository orderitemRepository ;
	@Override
	public List<OrderItem> addOrderItem(String email ) {
		
		MyUser user = userRepo.findByEmail(email);
		
		List<CartItem> prodList = cartRepo.findByUser(user);
		
		MyOrder order = new MyOrder();
		order.setUser(user);
		order.setCreatedAt(LocalDateTime.now());
		orderRepo.save(order);

		
		double totalSum = 0;
		
		
		for(CartItem item : prodList) {
			
			if (item.getProducts().getStock() < item.getQuantity()) {
				
				throw new RuntimeException("quantity is more than stock or product is not in stock");
			}
			
			OrderItem orderitem = new OrderItem();
			
			item.getProducts().setStock(item.getProducts().getStock() - item.getQuantity());
			
			orderitem.setProduct(item.getProducts());
			orderitem.setPrice(item.getProducts().getPrice());
			orderitem.setQuantity(item.getQuantity()); 
			orderitem.setOrder(order);
			
			orderitemRepository.save(orderitem);
			
			totalSum += item.getProducts().getPrice() * item.getQuantity() ;

			
			
		}
		
		order.setTotalAmount(totalSum);
		order.setStatus("PLACED");
		
		orderRepo.save(order);
		
		cartRepo.deleteAll(prodList);
		
		
		
		
		return orderitemRepository.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
