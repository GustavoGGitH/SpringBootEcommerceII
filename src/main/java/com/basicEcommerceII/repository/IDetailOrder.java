package com.basicEcommerceII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basicEcommerceII.model.OrderDetail;



@Repository
public interface IDetailOrder extends JpaRepository<OrderDetail,Integer> {

}
