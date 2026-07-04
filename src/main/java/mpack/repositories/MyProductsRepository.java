package mpack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mpack.entities.MyProducts;

public interface MyProductsRepository extends JpaRepository<MyProducts, Integer> {
	
	public Page<MyProducts> 	findByCategory (String category , Pageable  pageable );

}
