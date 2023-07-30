package com.basicEcommerceII.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basicEcommerceII.model.OrderDetail;
import com.basicEcommerceII.repository.IDetailOrder;



@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
	
	@Autowired
	private IDetailOrder iDetailOrder;

	@Override
	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return iDetailOrder.save(orderDetail);
	}
	
	

}
