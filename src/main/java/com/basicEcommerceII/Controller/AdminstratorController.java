package com.basicEcommerceII.Controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basicEcommerceII.Service.IOrderService;
import com.basicEcommerceII.Service.IProductService;
import com.basicEcommerceII.Service.IUserService;
import com.basicEcommerceII.model.Order;
import com.basicEcommerceII.model.Product;
import com.basicEcommerceII.model.User;



@Controller
@RequestMapping("/administrator")

public class AdminstratorController {
	
	private Logger logg= LoggerFactory.getLogger(AdminstratorController.class);
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;
	

	
	@GetMapping("")
	public String home(Model model) {
		
		logg.info("Redireccione al controlador /administrator");
		
		List<Product> products= productService.findAll();
		
		model.addAttribute("products", products);
		
		return "administrator/home";
		
	}
	
	@GetMapping("/users")
	public String users(Model model) {
	    List<User> users = userService.findAll();
	    System.out.println("Total users: " + users.size()); // Verificar el número de usuarios obtenidos
	    model.addAttribute("users", users);
	    return "administrator/users";
	}

	@GetMapping("/orders")
	public String getorders(Model model) {
		
	/*	model.addAttribute("session", session.getAttribute("idusuario"));
			
		User user= userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();*/
		
		List<Order> orders= orderService.findAllOrder();
		 System.out.println("Total ordenes: " + orders.size());
		
		model.addAttribute("orders",orders);
		return "/administrator/ordenes";
	}

	@GetMapping("/detailorder/{id}")
	public String detalleOrder(Model model,@PathVariable  Integer id ) {
		
		logg.info("El id de la orden obtenida es {}:", id);
		
		 Order order= orderService.findById(id).get();
		 model.addAttribute("detalles", order.getDetalle());
		return "/administrator/detalleorden";
		
		
		
	}





}