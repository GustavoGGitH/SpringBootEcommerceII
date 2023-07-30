package com.basicEcommerceII.Service;

import org.springframework.stereotype.Service;

import com.basicEcommerceII.model.OrderDetail;



@Service
public interface IOrderDetailService {
	
	//Solamente declaro el método , en este caso es saveOrderDetail que permite guardar la entidad Order Detail
	OrderDetail saveOrderDetail(OrderDetail orderDetail);

}
