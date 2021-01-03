package com.springboot.h2.serv.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.h2.model.Product;
import com.springboot.h2.repo.ProductRepository;
import com.springboot.h2.serv.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	ProductRepository repository;
	
	// Save product entity in the h2 database.
	@Override
	public Product saveProduct(Product prod) {
		// TODO Auto-generated method stub
		return repository.save(prod);		
	}

// -----------------------------------------	
	// EDIT /UPDATE products
	@Override
	public Product updateProduct(Product product) {
		
		//Optional<Product> s=repository.findById(product.getProductId());
		// if(null!=s) {
			return repository.saveAndFlush(product);
		
	}

//--------------------------------------------------------------
	
	// delete product
	@Override
	public void deleteProduct(final int productId) {
		repository.deleteById(productId);
	}

	// ------------------------------------------------------------------------------------
	// GET
	
	// Get all products by isHidden from the h2 database.  = 1 hidden  0 visible
	@Override
	public List<Product> getAllProductsByIsHidden(int isHidden) {
		return repository.getAllProductsByIsHidden(isHidden);
	}

	@Override
	public List<Product> getAllProducts() {
		final List<Product> prodList = new ArrayList<>();
		repository.findAll().forEach(student -> prodList.add(student));
		return prodList;
	
	}
	
	@Override
	public Optional<Product> getProductById(int prodId) {
		return repository.findById(prodId);
	
	}

	@Override
	public List<Product> getProductByProdIdAndIsHidden(int productId, int isHidden) {	
		return repository.getProductsByProductIdAndIsHidden(productId, isHidden);
	}
	
	
}
