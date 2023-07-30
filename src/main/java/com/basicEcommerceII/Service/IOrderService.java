package com.basicEcommerceII.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.basicEcommerceII.model.Order;
import com.basicEcommerceII.model.User;



@Service
public interface IOrderService {
	
	//Declaro la implementación , en este caso del método save el cual voy a implementar en una clase específica de service
	 Order saveOrder(Order order) ;
	 
	 //Obtiene una lista de ordenes
	 List<Order> findAllOrder();
	 
	 // Permite generar numero de orden
	  String generarNumeroOrden() ;
	  
	  
	  //Obtiente lista de ordenes según el usuario
	  List<Order> findByUser(User user);
	  
	  Optional<Order> findById(Integer id);
}
