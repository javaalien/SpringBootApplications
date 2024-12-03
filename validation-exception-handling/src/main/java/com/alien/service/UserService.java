package com.alien.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.dto.UserRequest;
import com.alien.entity.User;
import com.alien.exception.UserNotFoundException;
import com.alien.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User saveUser(UserRequest userRequest) {
		User user = User.build(0, userRequest.getName(), userRequest.getEmail(), userRequest.getMobile(),
				userRequest.getGender(), userRequest.getAge(), userRequest.getNationality());
		return repository.save(user);
	}

	public List<User> getALlUsers() {
		return repository.findAll();
	}

	public User getUser(int id) throws UserNotFoundException {
		User user = repository.findByUserId(id);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User not found with id :" + id);
		}
	}

}
