package com.springboot.h2.security;

import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDtlsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncode());
		return authProvider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		 //auth.authenticationProvider(authenticationProvider());
		
		  //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		new BCryptPasswordEncoder().encode("1234");
		  auth.inMemoryAuthentication().withUser("Admin").password(new BCryptPasswordEncoder().encode("1234"))
		  .roles("Admin").and().withUser("foo").password(new BCryptPasswordEncoder().encode("foo")).roles("Customer");
		  //encoder.encode("1234") .roles("Admin");
		 
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
		super.configure(web);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		//.antMatchers("/").hasAnyAuthority("admin","Admin")  //ADMIN","admin")
		//.antMatchers("/").hasAnyAuthority("/*")
		//.antMatchers("/admin/**").hasAnyAuthority("admin","Admin")    // permit only admin links
		//.antMatchers("/admin/").hasAnyAuthority("Admin","admin")		
		//.antMatchers("/admin/delete/**").hasAnyAuthority("Admin","admin")			// only admin can delete
		//.antMatchers("/admin/update/**").hasAnyAuthority("Admin","admin")			// only admin can update
		.antMatchers("/h2-console/**").permitAll()									
		//.anyRequest().authenticated()
		.and().formLogin().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //permitAll();
		.and().authorizeRequests().anyRequest().authenticated()
		.and().httpBasic();
		//.and().logout().permitAll()
		//.and().exceptionHandling();
		//.accessDeniedPage("/403");
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
}

