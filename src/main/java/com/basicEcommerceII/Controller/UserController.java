package com.basicEcommerceII.Controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basicEcommerceII.Service.IOrderService;
import com.basicEcommerceII.Service.IUserService;
import com.basicEcommerceII.model.Order;
import com.basicEcommerceII.model.User;






@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderService orderService;
	
	
		
	BCryptPasswordEncoder passEncoder= new BCryptPasswordEncoder();

	// Para acceder al siguiente métod es con la url /user/register, siempre con el
	// puerto 8080
	@GetMapping("/register")

	public String createregister() {

		return "user/registro";
	}

	@PostMapping("/save")

	public String saveregister(User user) {

		logger.info("Usuario registro recibo de register:  {}", user);
		user.setTipo("USER");
		user.setPassword(passEncoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
		logger.info("Usuario registro recibo de register y luego hago encoder de password:  {}", user);
		userService.saveUser(user);

		return "redirect:/";

	}

	 // Desde el video 50 cambio el acceso desde el html login desde el endpoint /acceder al end point /login, paso de usar
	// una autenticacion "manual" a utilizar spring security
	@GetMapping("/login")
	public String login() {

		return "user/login";
	}
	
	// A partir de que uso spring security (50) dejo de utilizar el mail para obtener el usuario y paso a usar el id


	
	
/*	 @GetMapping("/acceder")
	    public String acceder(User user, HttpSession session) {
	        logger.info("Accesos: {}", user);

	        // Realizar la autenticación del usuario (probablemente ya se ha realizado con Spring Security)

	        Optional<User> usuario = userService.findByMail(user.getMail());
	        if (usuario.isPresent()) {
	            session.setAttribute("idusuario", usuario.get().getId());
	            if (usuario.get().getTipo().equals("ADMIN")) {
	                return "redirect:/administrator";
	            } else {
	                return "redirect:/";
	            }
	        } else {
	            logger.info("User does not exist");
	        }

	        return "redirect:/";
	    }*/

	 @GetMapping("/acceder")
	    public String acceder(User user,HttpSession session) {
	        logger.info("Accesos: {}", user);
	        
	        Optional<User> usuario = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
	      //  logger.info("Usuario de deb: {}", usuario.get());
	        
	        if (usuario.isPresent()) {
	            session.setAttribute("idusuario", usuario.get().getId());
	            if (usuario.get().getTipo().equals("ADMIN")) {
	            	  logger.info("valido igual a usuario ADMIN: {}", usuario.get());
	                return "redirect:/administrator";
	               
	            } else {
	                return "redirect:/";
	            }
	        } else {
	            logger.info("User does not exist");
	        }
	        logger.info("salio del if de acceder: ");
	        return "redirect:/";
	    }
	        
	 
	 
	 
	@GetMapping("/compras")
	public String getcompras(Model model, HttpSession session) {

		model.addAttribute("session", session.getAttribute("idusuario"));

		User user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

		List<Order> orders = orderService.findByUser(user);

		model.addAttribute("orders", orders);
		return "/user/compras";
	}

	// Permite obtener el detalle de la orde por ID
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la orden: {}", id);
		Optional<Order> orden = orderService.findById(id);

		model.addAttribute("detalles", orden.get().getDetalle());

		// session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "user/detallecompra";
	}
	// Permiete cerrar session del usuario

	@GetMapping("/close")
	public String closesession(HttpSession session) {

		session.removeAttribute("idusuario");
		
		   logger.info("valor del idusuario luego de ser destruido: {}",session.getAttribute("idusuario"));

		return "redirect:/";
	}

}
