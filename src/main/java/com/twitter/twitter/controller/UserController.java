package com.twitter.twitter.controller;

import com.twitter.twitter.entity.User;
import com.twitter.twitter.repository.UserRepository;
import com.twitter.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //TODO POST MAPPINGLERI YAP


    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

}
