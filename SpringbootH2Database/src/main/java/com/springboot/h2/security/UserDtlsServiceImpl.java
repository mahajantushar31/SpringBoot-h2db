package com.springboot.h2.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.h2.model.CommonUser;
import com.springboot.h2.repo.CommonUserRepository;
import com.springboot.h2.serv.CommonUserService;

@Service
public class UserDtlsServiceImpl implements UserDetailsService {

	
	 @Autowired private CommonUserRepository commonUserRepository;
	 
	@Autowired
	private CommonUserService commonUserServie;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CommonUser user=commonUserServie.getUserByUserName(username);
		if(null==user) {
			throw new UsernameNotFoundException("Could not Find user"+username);
		}
		return new MyUserDetails(user);
	}


	public String getUserRole(){
		 String userRoleStr="";
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 /*  System.out.println(" name "+auth.getName());
			 System.out.println(" cred "+auth.getCredentials());
			 * System.out.println(" principle  "+auth.getPrincipal());
			 * System.out.println(" authToStr  "+auth.toString());
			 */
		 if(null!=auth && null!=auth.getAuthorities() && (userRoleStr).equalsIgnoreCase("ROLE_Admin")) { 
		 List<Object> lst=(List<Object>)auth.getAuthorities(); // ).get(0)
		 	userRoleStr=(null!=lst && !lst.isEmpty())?(String) lst.get(0):"";
		System.out.println("\n dtls "+auth.getDetails()+"\n auth:"+userRoleStr); //ROLE_Admin   ROLE_Admin ,  Role_Customer
		 }
		return userRoleStr;
		 
	 }
	
}
