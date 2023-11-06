package com.twitter.twitter.service;


import com.twitter.twitter.entity.User;
import com.twitter.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
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
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null; //TODO Exception...
    }

    @Override
    public User saveUser(User user) {
       boolean email = userRepository.emailChecker(user.getEmail());
       if(email){
           throw new RuntimeException("Email already exist :" + user.getEmail());
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
