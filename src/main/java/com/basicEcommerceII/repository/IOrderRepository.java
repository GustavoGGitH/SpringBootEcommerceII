package com.basicEcommerceII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basicEcommerceII.model.Order;
import com.basicEcommerceII.model.User;



@Repository
public interface IOrderRepository extends JpaRepository <Order, Integer>{
	
	List<Order> findByUsuario(User user);

}
