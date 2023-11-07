package com.twitter.twitter.controller;

import com.twitter.twitter.dto.UserResponse;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.repository.UserRepository;
import com.twitter.twitter.service.UserService;
import com.twitter.twitter.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/profile")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        return Converter.userResponseConverter(userService.saveUser(user));
    }

    //TODO POST MAPPINGLERI YAP

    @PostMapping("/login")
    public UserResponse login(@RequestBody User user){
        User user1 = userService.findUserByEmail(user.getEmail());
        if(user1.getPassword() == user.getPassword()){

        }

    }

    @DeleteMapping("/{id}")
    public UserResponse deleteUser(@PathVariable int id){
        return Converter.userResponseConverter(userService.deleteUser(id));
    }

}
