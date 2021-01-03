package com.springboot.h2.ctrl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.h2.commons.ResponseCode;
import com.springboot.h2.dto.ResponseDto;
import com.springboot.h2.model.Product;
import com.springboot.h2.security.UserDtlsServiceImpl;
import com.springboot.h2.serv.ProductService;

@RestController // Useful to create the RESTful webservices.
@RequestMapping
public class ProductController {

	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	ProductService productService;

	@Autowired
	UserDtlsServiceImpl userDtlsServiceImpl;

	
	// ------------------------------------------------------------------------------
	// Save product entity in the h2 database.
	// @PostMapping annotation handles the http post request matched with the given uri.
	// @RequestBody annotation binds the http request body to the domain object.
	// @Valid annotation validates a model after binding the user input to it.
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_Admin')") //or hasAuthority('ROLE_CUSTOMER')")
	@PostMapping(value= {"/prod/create"})
	public Product addProduct(final @RequestBody @Valid Product productObject) {
		log.info("Saving Product details in the database.");
		return productService.saveProduct(productObject);
	}
	
	
	// ------------------------------------------------------------------------------------
	// GET  
	@GetMapping(value= {"/prod/all"})
	public ResponseDto getAllProductForCustomer() {
		ResponseDto responseDto=new ResponseDto();
		String message="Status Success";
		String code="200";
		Object response=null;
		String roleAuth=userDtlsServiceImpl.getUserRole();
		
		log.info("Geting all product  by  "+roleAuth);
		 if(null!=roleAuth && !roleAuth.trim().isEmpty() && (roleAuth).equalsIgnoreCase("ROLE_Admin")) {
			 response=productService.getAllProducts();
			 // userRoleObj=new UserRole(1,"Admin","All Access");
			 return new ResponseDto(code, message, response);
		 }else {
			 productService.getAllProductsByIsHidden(0);    // only visible
			return new ResponseDto(code, message, response);
		 }
	
	}

	@RequestMapping(value= {"/prod/{prodId}"})
	public ResponseDto getProduct(final @PathVariable @Valid int prodId) {
		ResponseDto responseDto=new ResponseDto();
		String message="Updated status Success";
		String code="200";
		Object response=null;
		String roleAuth=userDtlsServiceImpl.getUserRole();
		
		log.info("Geting all product for "+roleAuth+" prod details in the database for prodId: "+prodId);
		 if(null!=roleAuth && !roleAuth.trim().isEmpty() && (roleAuth).equalsIgnoreCase("ROLE_Admin")) {
			 response=productService.getAllProducts();
			 return new ResponseDto(code, message, response);
		 }else {
			 response=productService.getProductByProdIdAndIsHidden(prodId, 0);
			 return new ResponseDto(ResponseCode.ER411.getCode(), ResponseCode.ER411.getMessage(), response);
		 }
		
	}

	//----------------------------------------------------------------------------------
	// UPDATE 
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_Admin')") //or hasAuthority('ROLE_CUSTOMER')")
	@PostMapping(value= "/prod/update")
	public Product updateProd(final @RequestBody @Valid Product product) {
		
		log.info("Updateing product details in the database.");
		return productService.updateProduct(product);
	}
	// -----------------------------------------------------------------------------------
	// DELETE
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_Admin')") //or hasAuthority('ROLE_CUSTOMER')")
	@RequestMapping(value= "/prod/delete/{prodId}")
	public String deleteProduct(final @PathVariable("prodId") @Valid int productId) {
		log.info("Deleting product details from the database.");
		productService.deleteProduct(productId);
		return "product Delete with id: "+productId;
		
	}
	
	
}
