package com.basicEcommerceII.Controller;

import java.io.IOException;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.basicEcommerceII.Service.IUserService;
import com.basicEcommerceII.Service.ProductServiceImpl;
import com.basicEcommerceII.Service.UploadFileService;
import com.basicEcommerceII.model.Product;
import com.basicEcommerceII.model.User;



@Controller

@RequestMapping("/products")
public class ProductController {

	@Autowired
	public ProductServiceImpl	 productService;

	@Autowired
	public UploadFileService upload;

	@Autowired
	private IUserService userService;
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	

	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("products", productService.findAll());
		return ("products/show");
	}

	@GetMapping("/create")
	public String create() {
		return ("products/create");
	}

	@PostMapping("/save")
	public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("Este es el objeto producto {}", product);
		
		if (session.getAttribute("idusuario")!=null) {
		// Obtengo el usuario de la session parseandolo a integer y luego haciendo un finbyid según método del userService
		User u= userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
	

		product.setUsuario(u);
		if (product.getCod_articulo() == null) { // Cuando se crea un artículo su clave es nula

			String nombre_image = upload.saveImage(file);
			product.setImage(nombre_image);

		} else {

		}

		productService.save(product);
		return "redirect:/products";
		} else {
			
		return "redirect:/products";	
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product product = new Product();

		Optional<Product> optionalProduct = productService.get(id);
		product = optionalProduct.get();

		LOGGER.info("Producto buscado: {}", product);
		model.addAttribute("product", product);

		return ("products/edit");
	}
	/*
	 * @PostMapping("/update") public String update(Product product) {
	 * productService.update(product);
	 * LOGGER.info("Este es el objeto producto modificado {}", product); return
	 * "redirect:/products";
	 * 
	 * }
	 */

	@PostMapping("/update")
	public String update(@RequestParam Integer id, @RequestParam String nombre, @RequestParam String descripcion,
			@RequestParam Float cantidad, @RequestParam Double precio, @RequestParam("img") MultipartFile file)
			throws IOException {
		Optional<Product> optionalProduct = productService.get(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setNombre_articulo(nombre);
			product.setNombre_det_Art(nombre);
			product.setCantidad(cantidad);
			product.setPrecio_unit_ing(precio);

			Product p = new Product();
			p = productService.get(product.getCod_articulo()).get();

			if (file.isEmpty()) { // cuando editamos el producto y no cambiamos imagen

				product.setImage(p.getImage());

			} else { // Cuando se edita la imagen

				// Acá elimino la imagen cuando no sea la imagen por defecto la que viene
				if (!p.getImage().equals("default.jpg")) {
					upload.deleteImage(p.getImage());

				}

				String nombre_image = upload.saveImage(file);
				product.setImage(nombre_image);

			}
			product.setUsuario(p.getUsuario());
			productService.update(product);
			LOGGER.info("Este es el objeto producto modificado: {}", product);
		}

		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) throws IOException {

		Product p = new Product();
		p = productService.get(id).get();
		Optional<Product> optionalProduct = productService.get(id);
		if (optionalProduct.isPresent()) {
			// Acá elimino la imagen cuando no sea la imagen por defecto la que viene
			if (!p.getImage().equals("default.jpg")) {
				upload.deleteImage(p.getImage());

			}

			productService.delete(id);

		}

		return "redirect:/products";
	}

}