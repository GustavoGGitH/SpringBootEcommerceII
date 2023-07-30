package com.basicEcommerceII.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.basicEcommerceII.model.Product;
import com.basicEcommerceII.repository.IProductRepository;




@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	public IProductRepository productRepository;

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> get(Integer cod_articulo) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(productRepository.findById(cod_articulo)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Product %s not found ", cod_articulo))));
	}



	@Override
	public void delete(Integer cod_articulo) {
		productRepository.deleteById(cod_articulo);
		
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
		
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	
	
	

}
