package com.login.service;

import com.login.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);
}
