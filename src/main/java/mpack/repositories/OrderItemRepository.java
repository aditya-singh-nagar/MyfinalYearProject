package mpack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mpack.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	

}
