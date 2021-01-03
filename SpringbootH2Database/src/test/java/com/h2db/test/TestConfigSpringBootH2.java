package com.h2db.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.h2.serv.CommonUserService;
import com.springboot.h2.serv.ProductService;
import com.springboot.h2.serv.impl.CommonUserServiceImpl;
import com.springboot.h2.serv.impl.ProductServiceImpl;

@Configuration
public class TestConfigSpringBootH2 {

    // this bean will be injected into the OrderServiceTest class
	@Bean
    public CommonUserService commonUserService() {
		CommonUserService commonUserService = new CommonUserServiceImpl();
        // set properties, etc.
        return commonUserService;
    }
	
	@Bean
    public ProductService productService() {
		ProductService productService = new ProductServiceImpl();
        // set properties, etc.
        return productService;
    }
	
    /*
	@Bean
    public OrderService orderService() {
        OrderService orderService = new OrderServiceImpl();
        // set properties, etc.
        return orderService;
    }
    */
}