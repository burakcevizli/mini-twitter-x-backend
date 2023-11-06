package com.twitter.twitter.service;


import com.twitter.twitter.entity.User;
import com.twitter.twitter.exceptions.TwitterException;
import com.twitter.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserId(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new TwitterException("id is not found...", HttpStatus.NOT_FOUND);
    }

    @Override
    public User saveUser(User user) {
        String email = userRepository.emailChecker(user.getEmail());
        String phone = userRepository.phoneChecker(user.getPhone());
        String username = userRepository.usernameChecker(user.getUserName());
        if (email != null) {
            throw new TwitterException("User is already exist this email : " + email, HttpStatus.BAD_REQUEST);
        }
        if (phone != null) {
            throw new TwitterException("User is already exist with this phone : " + phone, HttpStatus.BAD_REQUEST);
        }
        if (username != null) {
            throw new TwitterException("User is already exist with this username : " + username, HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(int id) {
        User user = findByUserId(id);
        userRepository.delete(user);
        return user;
    }
}
