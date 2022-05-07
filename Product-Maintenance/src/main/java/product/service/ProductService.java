package product.service;

import java.util.List;

import product.model.Product;

public interface ProductService {

	void deleteProductById(Long id);

	Product getProductById(Long id);

	Product updateProduct(Product product);

	Product saveProduct(Product product);

	List<Product> getAllProducts();

}
