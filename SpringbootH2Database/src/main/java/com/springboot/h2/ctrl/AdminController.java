package com.springboot.h2.ctrl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.h2.commons.CommonConstants;
import com.springboot.h2.commons.ResponseCode;
import com.springboot.h2.dto.ResponseDto;
import com.springboot.h2.model.CommonUser;
import com.springboot.h2.model.UserRole;
import com.springboot.h2.security.UserDtlsServiceImpl;
import com.springboot.h2.serv.CommonUserService;
//import com.springboot.h2.serv.StudentService;

@RestController // Useful to create the RESTful webservices.
@RequestMapping
public class AdminController{

	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	CommonUserService commonUserService;

	@Autowired
	UserDtlsServiceImpl userDtlsServiceImpl;
	
	 @RequestMapping(value="/")
	 public String login() {
	 
		 System.out.println("******************logging************************");
		 
	    return "login";
	 }

	 
	 // get user Auth details from security context
	 
	 
	// Save User entity in the h2 database.
	// @PostMapping annotation handles the http post request matched with the given uri.
	// @RequestBody annotation binds the http request body to the domain object.
	// @Valid annotation validates a model after binding the user input to it.
	@PostMapping(value= {"/user/create"})
	//@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_Admin') or hasAuthority('ROLE_CUSTOMER')")
	public CommonUser createUser(final @RequestBody @Valid CommonUser userObject) {
		
		UserRole userRoleObj=null;
		String roleAuth=userDtlsServiceImpl.getUserRole();
		 if(null!=roleAuth && !roleAuth.trim().isEmpty() && (roleAuth).equalsIgnoreCase("ROLE_Admin")) {
			 userObject.getUserRoleSet().add(new UserRole(1,"Admin","All Access"));
			 // userRoleObj=new UserRole(1,"Admin","All Access");
		 }else {
			 userObject.getUserRoleSet().add(new UserRole(1,"Customer","Limited Access"));
		 }
		 log.info("Saving CommonUser details in the database with role"+roleAuth);
		 return commonUserService.saveCommonUser(userObject);
	}
	
	// -----------------------------------------------------------------
	//@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_Admin')" )
	@RequestMapping(value= "/user/{userId}")
	public ResponseDto getUser(final @PathVariable @Valid int userId) {
		ResponseDto responseDto=new ResponseDto();
		String message="Success";
		String code="200";
		Object response=null;
		String roleAuth=userDtlsServiceImpl.getUserRole();
		 if(null!=roleAuth && !roleAuth.trim().isEmpty() && (roleAuth).equalsIgnoreCase("ROLE_Admin")) {
			 response=commonUserService.getCommonUserByUserId(userId);
			 // userRoleObj=new UserRole(1,"Admin","All Access");
			 return new ResponseDto(code, message, response);
		 }else {
			message =CommonConstants.NOT_AUTH_FOR_SERVICE_MSG;
			return new ResponseDto(ResponseCode.ER411.getCode(), ResponseCode.ER411.getMessage(), response);
		 }
		 
	}
	
	// Get all users from the h2 database.
		// @GetMapping annotation handles the http get request matched with the given uri.
		
		//@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
		@GetMapping(value= "/user/all") //, produces= "application/vnd.jcg.api.v1+json")
		public ResponseDto getAllUser() {
			ResponseDto responseDto=new ResponseDto();
			String message="Success";
			String code="200";
			Object response=null;
			String roleAuth=userDtlsServiceImpl.getUserRole();
			
			 if(null!=roleAuth && !roleAuth.trim().isEmpty() && (roleAuth).equalsIgnoreCase("ROLE_Admin")) {
				 response=commonUserService.getAllCommonUsers();
				 // userRoleObj=new UserRole(1,"Admin","All Access");
				 return new ResponseDto(code, message, response);
			 }else {
				
				return new ResponseDto(ResponseCode.ER411.getCode(), ResponseCode.ER411.getMessage(), response);
			 }
			
		}
		
	// ------------------------------------------------------
	// UPDATE 	
	@PatchMapping(value= "/user/update")
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	public ResponseDto updateUser(final @RequestBody @Valid CommonUser commonUser) {
		ResponseDto responseDto=new ResponseDto();
		String message="Updated status Success";
		String code="200";
		Object response=null;
		String roleAuth=userDtlsServiceImpl.getUserRole();
		
		 if(null!=roleAuth && !roleAuth.trim().isEmpty() && (roleAuth).equalsIgnoreCase("ROLE_Admin")) {
			 response=commonUserService.updateCommonUser(commonUser);
			 // userRoleObj=new UserRole(1,"Admin","All Access");
			 return new ResponseDto(code, message, response);
		 }else {
			return new ResponseDto(ResponseCode.ER411.getCode(), ResponseCode.ER411.getMessage(), response);
		 }
	
	
	}
	
	// --------------------------------------------------------
	// DELETE 
	@RequestMapping(value= "/user/delete/{userId}")
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	public String deleteUser(final @PathVariable @Valid int userId) {
		log.info("Deleting User details from the database.");
		commonUserService.deleteCommonUser(userId);
		return "User Delete Success";
		
	}
	
	
}
