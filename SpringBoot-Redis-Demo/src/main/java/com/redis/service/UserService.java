package com.redis.service;

import java.util.List;

import com.redis.model.User;

public interface UserService {

    boolean saveUser(User user);

    List<User> fetchAllUser();

    User fetchUserById(Long id);

    boolean deleteUser(Long id);

    boolean updateUser(Long id, User user);
}
