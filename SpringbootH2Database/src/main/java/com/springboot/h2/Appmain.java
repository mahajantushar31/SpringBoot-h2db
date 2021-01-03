package com.springboot.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.h2.model.CommonUser;
import com.springboot.h2.model.Product;
import com.springboot.h2.model.UserRole;
import com.springboot.h2.repo.CommonUserRepository;
import com.springboot.h2.repo.ProductRepository;

@SpringBootApplication(scanBasePackages = "com.springboot.h2")
public class Appmain implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Appmain.class, args);
	}

	@Autowired
	CommonUserRepository commonUserRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// userId, String userName, int pass
		
		CommonUser user=new  CommonUser(1,"Admin","1234",true);
	
		
		// roleId, String roleName, String roleDesc
		UserRole userRole=new UserRole(1,"Admin","Admin All acess");
		//Set<UserRole> sRole=new HashSet<UserRole>();
		user.getUserRoleSet().add(userRole);
	//user.getUserRoleList().add(userRole);
		commonUserRepository.save(user);
	
	// product   //productId, String productName, double price, String productDesc, int isHiden
	Product prod=new Product(1,"Book Of life ",101.51,"Novel ",0);
	productRepository.save(prod);
		
	}
	
}
