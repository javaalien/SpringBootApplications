package com.alien.service;

import java.util.List;

import com.alien.dto.UserDto;
import com.alien.entity.User;

public interface UserService {
	
	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();
	
}