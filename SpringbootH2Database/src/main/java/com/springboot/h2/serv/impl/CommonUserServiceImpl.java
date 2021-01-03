package com.springboot.h2.serv.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.h2.model.CommonUser;
import com.springboot.h2.repo.CommonUserRepository;
import com.springboot.h2.serv.CommonUserService;

@Service
public class CommonUserServiceImpl implements CommonUserService{
	
	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	CommonUserRepository userRepository;

	// Save product entity in the h2 database.
	@Override
	public CommonUser saveCommonUser(final CommonUser user) {
		return userRepository.save(user);
	}

	// EDIT /UPDATE products
	@Override
	public CommonUser updateCommonUser(CommonUser userObject) {
		Optional<CommonUser> s=userRepository.findById(userObject.getUserId());
		if(null!=s) {
			return userRepository.saveAndFlush(userObject);
		}
		return null;
	}

	// delete product
	@Override
	public void deleteCommonUser(int userId) {
		userRepository.deleteById(userId);
	}
	
	// Get all products from the h2 database.
	@Override
	public List<CommonUser> getAllCommonUsers() {
		final List<CommonUser> usersList = new ArrayList<>();
		userRepository.findAll().forEach(user -> usersList.add(user));
		return usersList;
	}

	@Override
	public Optional<CommonUser> getCommonUserByUserId(int userId) {
		return userRepository.findById(userId);
	}
	
	@Override
	public CommonUser getUserByUserName(String username) {
		try {
			System.out.println(" username "+username);
					CommonUser u=userRepository.getUserByUserName(username);
					System.out.println(" u "+u.toString());
					return u; 
		}catch(Exception e) {
			System.out.println("ERROR "+e);
		}
		return null;
	}
}
