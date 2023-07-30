package com.basicEcommerceII.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.basicEcommerceII.Service.IOrderDetailService;
import com.basicEcommerceII.Service.IOrderService;
import com.basicEcommerceII.Service.IProductService;
import com.basicEcommerceII.Service.IUserService;
import com.basicEcommerceII.model.Order;
import com.basicEcommerceII.model.OrderDetail;
import com.basicEcommerceII.model.Product;
import com.basicEcommerceII.model.User;


@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductService productService;

    @Autowired	
    private IUserService userService;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IOrderDetailService iOrderDetail;

    List<OrderDetail> detail = new ArrayList<OrderDetail>();
    Order order = new Order();

	@GetMapping("")
	public String home(Model model,HttpSession session) {
		
		log.info("La Sesion del usuario en el getmapping del home : {} ", session.getAttribute("idusuario"));
		model.addAttribute("products", productService.findAll());
		
		//agrego sesion al model que le voy a pasar a la vista
		
		Integer idUsuario = (Integer) session.getAttribute("idusuario");
		model.addAttribute("session", idUsuario);
		
		log.info("Valor de 'idusuario' en la sesión estoy en el punto ante de redireccionar a user/home: {}", session.getAttribute("idusuario"));

		// List<Product> products = productService.findAll();

		// model.addAttribute("products", products);

		return "user/home";

	}
    @GetMapping("productohome/{id}")
    public String productHome(@PathVariable Integer id, Model model) {
        log.info("Id Enviado como parámetro {}", id);
        Optional<Product> productOptional = productService.get(id);
        Product product = productOptional.orElse(new Product());
        model.addAttribute("product", product);
        return "user/productohome";
    }

    @PostMapping("/cart")
    public String addCar(@RequestParam Integer id, @RequestParam float cantidad, Model model,HttpSession session) {
        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product();
        double sumaTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);

        log.info("Prducto añadido {}", optionalProduct.orElse(null));
        log.info("Cantidad {}", cantidad);

        product = optionalProduct.orElse(new Product());

        orderDetail.setCantidad(cantidad);
        orderDetail.setPrecio(product.getPrecio_unit_ing());
        orderDetail.setNombre(product.getNombre_articulo());
        orderDetail.setTotal(product.getPrecio_unit_ing() * cantidad);
        orderDetail.setProductos(product);

        Integer idProducto = product.getCod_articulo();
        boolean ingresado = detail.stream().anyMatch(p -> p.getProductos().getCod_articulo() == idProducto);

        if (!ingresado) {
            detail.add(orderDetail);
        }

        sumaTotal = detail.stream().mapToDouble(dt -> dt.getTotal()).sum();
        order.setTotal(sumaTotal);

        model.addAttribute("cart", detail);
        model.addAttribute("order", order);

        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "user/carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model) {
        List<OrderDetail> ordenesNueva = new ArrayList<OrderDetail>();

        for (OrderDetail detalleOrden : detail) {
            if (detalleOrden.getProductos().getCod_articulo() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }

        detail = ordenesNueva;
        double sumaTotal = 0;
        sumaTotal = detail.stream().mapToDouble(dt -> dt.getTotal()).sum();
        order.setTotal(sumaTotal);

        model.addAttribute("cart", detail);
        model.addAttribute("order", order);

        return "user/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        model.addAttribute("cart", detail);
        model.addAttribute("order", order);
        model.addAttribute("session", session);
        return "user/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        
    	// Aquí tomo la session del usuario que es un objeto, lo paso a string y luego lo parseo a integer para fecién ahí si pasarlo como parámetro a findbyID
		
    	if (session.getAttribute("idusuario")!=null) {
	    	User user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
			log.info("Sesion del usuario : {} ", session.getAttribute("idusuario"));
	        model.addAttribute("cart", detail);
	        model.addAttribute("order", order);
	        model.addAttribute("user", user);
	        return "user/resumenorden";} 
    	else { 
    		return "user/resumenorden";
    	}
    }	

	@GetMapping("/saveOrder")

	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();
		// Obtengo el usuario
		User user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		log.info("Estoy obteniendo el id de usuario en el método salvarorden : {} ", session.getAttribute("idusuario"));
		order.setFechadecreacion(fechaCreacion);
		order.setNumero(iOrderService.generarNumeroOrden());
		order.setUsuario(user);
		log.info("Estoy salvando la orden con el id de usuario : {} ", session.getAttribute("idusuario"));
		iOrderService.saveOrder(order);

		// Guardamos detalle de la orden
		// Recorro todo el detalle y voy guardando la orden

		for (OrderDetail dt : detail) {

			dt.setOrden(order);

			iOrderDetail.saveOrderDetail(dt);

		}
		// Limpiar orden y lista

		order = new Order();

		detail.clear();

		return "redirect:/";
	}

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        List<Product> products = productService.findAll()
                .stream()
                .filter(p -> p.getNombre_articulo() != null && p.getNombre_articulo().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("products", products);
        return "user/home";
    }
}