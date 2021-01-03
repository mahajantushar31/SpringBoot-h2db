package com.springboot.h2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.h2.model.CommonUser;

@Repository("userRepository")
public interface CommonUserRepository extends JpaRepository<CommonUser, Integer>{

	@Query(value="SELECT * FROM common_user  where user_name = :username",nativeQuery = true)
	CommonUser getUserByUserName(@Param("username") String username);

}
