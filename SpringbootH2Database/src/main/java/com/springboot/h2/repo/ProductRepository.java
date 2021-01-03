package com.springboot.h2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.h2.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(value="SELECT * FROM product e WHERE e.isHidden = :isHidden ",nativeQuery = true)
	List<Product> getAllProductsByIsHidden(int isHidden);


	@Query(value="SELECT * FROM product e WHERE e.productId=prductId AND e.isHidden = :isHidden ",nativeQuery = true)
	List<Product> getProductsByProductIdAndIsHidden(int productId,int isHidden);

}
