package product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.model.Product;
import product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired  
	ProductService productService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/products") 
	public String viewProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "viewProducts";
	}
	
	@PostMapping("/products") 
	public String viewProductsByAdd(@ModelAttribute("product") Product product,Model model){
		productService.saveProduct(product);
		return "redirect:/products";
	}
	
	@GetMapping("/updateProduct") 
	public String addProduct() {
		return "addProduct";
	}
	
	@GetMapping("/updateProduct/{id}") 
	public String updateProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "updateProduct";
	}
	
	@GetMapping("/deleteProduct/{id}") 
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "deleteProduct";
	}
	
	@PostMapping("/deleteProduct/{id}") 
	public String viewProductByDelete(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return "redirect:/products";
	}
	
	@RequestMapping("/deleteProduct")
	public String viewProductByNoDelete() {
		return "redirect:/products";
	}
	
	@PostMapping("/products/{id}") 
	public String viewProductsByUpdate(@PathVariable("id") Long id,
			@ModelAttribute("product") Product product){
		Product p = productService.getProductById(id);
		p.setDescription(product.getDescription());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		productService.saveProduct(p);
		return "redirect:/products";
	}
}
