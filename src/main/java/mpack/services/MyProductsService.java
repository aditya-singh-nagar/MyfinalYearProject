package mpack.services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mpack.dto.ProductAddDTO;
import mpack.entities.MyProducts;

public interface MyProductsService {
	
	public MyProducts  addProducts (ProductAddDTO prod);
	public Page<MyProducts> getAllProducts(int page , int size , String sortBy , String direction);
	public MyProducts   updateProducts (ProductAddDTO prod , int id);
	public MyProducts  deleteProduct (int id);
	public MyProducts  getOneProducts(int id);
	public Page<MyProducts> getAllProductsPagination (Pageable pageable);
	public Page<MyProducts>  getAllProductsLast (String category , Pageable pageable);

}
