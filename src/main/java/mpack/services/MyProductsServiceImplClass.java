package mpack.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mpack.dto.ProductAddDTO;
import mpack.dto.ProductRequestMapper;
import mpack.entities.MyProducts;
import mpack.repositories.MyProductsRepository;

@Service
public class MyProductsServiceImplClass implements MyProductsService {
	
	@Autowired
	private MyProductsRepository repo ;
	
	@Autowired
	private ProductRequestMapper mapper ;

	@Override
	public MyProducts addProducts(ProductAddDTO prod) {
		
		MyProducts myProducts = mapper.ProductDtoToProductEntity(prod);
		
		return repo.save(myProducts);
	}

	@Override
	public Page<MyProducts> getAllProducts(int page, int size, String sortBy, String direction) {
		
		Sort sort = direction.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable allData = PageRequest.of(page, size, sort);
		
		return repo.findAll(allData);
	}

	@Override
	public MyProducts updateProducts(ProductAddDTO prod , int id) {
		
		repo.findById(id).orElseThrow();
		
		MyProducts myproduct = mapper.ProductDtoToProductEntity(prod);
		myproduct.setId(id);
		return repo.save(myproduct);
	}

	@Override
	public MyProducts deleteProduct(int id) {
		Optional<MyProducts> product = repo.findById(id);
		product.orElseThrow();
		
	 repo.deleteById(id);
		return product.get();
	}

	@Override
	public MyProducts getOneProducts(int id) {
		
		Optional<MyProducts> opt = repo.findById(id);
		
		if(opt.get() == null){
			
			throw new NoSuchElementException();
		}
		
		return opt.get();
	}

	@Override
	public Page<MyProducts> getAllProductsPagination(Pageable pageable) {
		
		return repo.findAll(pageable);
	}

	@Override
	public Page<MyProducts> getAllProductsLast(String category, Pageable pageable) {
		
		return repo.findByCategory(category, pageable);
	}

}
