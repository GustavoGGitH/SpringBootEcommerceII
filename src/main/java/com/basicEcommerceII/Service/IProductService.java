package com.basicEcommerceII.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.basicEcommerceII.model.Product;




@Service
public interface IProductService {
	
	// método define alta de producto
	public Product save(Product product );
	
	// metódo para obtener un artículo según su código de artículo en este caso alfa
	// optional se implementa para resolver temas de manejo de nulos mas que nada
	public Optional<Product> get(Integer cod_articulo);
	
	// método que permite hacer un update de un producto
	public Product update( Product product);
	
	//método que permite hacer un delete del producto
	
	public void delete ( Integer cod_articulo);
	
	//método para obtener productos en base a una lista
	
	public List<Product> findAll();
}
