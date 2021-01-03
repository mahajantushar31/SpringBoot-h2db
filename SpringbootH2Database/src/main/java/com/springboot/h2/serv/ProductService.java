package com.springboot.h2.serv;

import java.util.List;
import java.util.Optional;

import com.springboot.h2.model.Product;

public interface ProductService {

	//SAVE
	Product saveProduct(Product product);
	
	// DELETE
	void deleteProduct(int productId);

	// UPDATE
	Product updateProduct(Product product);

	// GET
	List<Product> getAllProducts();

	Optional<Product> getProductById(int prodId);
	
	List<Product> getProductByProdIdAndIsHidden(int prodId,int isHidden);

	List<Product> getAllProductsByIsHidden(int isHddden);

}
