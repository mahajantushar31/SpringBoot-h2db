package com.springboot.h2.serv;

import java.util.List;
import java.util.Optional;

import com.springboot.h2.model.CommonUser;

public interface CommonUserService {

	CommonUser saveCommonUser(CommonUser userObject);

	List<CommonUser> getAllCommonUsers();

	CommonUser updateCommonUser(CommonUser userObject);

	void deleteCommonUser(int userId);

	Optional<CommonUser> getCommonUserByUserId(int userId);

	CommonUser getUserByUserName(String username);

}
