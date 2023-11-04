package com.twitter.twitter.service;


import com.twitter.twitter.entity.User;

import java.util.List;
public interface UserService {
    List<User> findAllUsers();

    User findByUserId(int id);

    User saveUser(User user);

    User deleteUser(int id);
}
