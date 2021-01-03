package com.springboot.h2.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.springboot.h2.model.CommonUser;
import com.springboot.h2.model.UserRole;

@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails {

	private CommonUser user;

	public MyUserDetails(CommonUser user) {
		this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	Set<UserRole> role=user.getUserRoleSet();
	List<SimpleGrantedAuthority> authorities=new ArrayList<>();
	System.out.println("\n ROLES "+role);
	role.forEach(x->authorities.add(new SimpleGrantedAuthority(x.getRoleName())));
	// (student -> prodList.add(student));
	
	return authorities;
	}

	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(user.getPassword());
		// encode(CharSequence rawPassword)
		// return user.getPassword(); for testing
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; //false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return  true; //false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true; //false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isActive();
	}

}
