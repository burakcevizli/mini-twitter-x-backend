package com.twitter.twitter.service;

import com.twitter.twitter.dto.LoginUserDto;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.exceptions.TwitterException;
import com.twitter.twitter.repository.UserRepository;
import com.twitter.twitter.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(encodedPassword);
        user1.setBirthday(user.getBirthday());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setUserName(user.getUsername());
        user1.setProfilePicture(user.getProfilePicture());
        user1.setProfileWallpaper(user.getProfileWallpaper());
        user1.setRegisterDate(user.getRegisterDate());
        user1.setPhone(user.getPhone());
        return userRepository.save(user1);
    };

    public User login(LoginUserDto loginUserDto){
        Optional<User> optionalUser = userRepository.findUserByEmail(loginUserDto.email());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            boolean isPasswordSame = passwordEncoder.matches(loginUserDto.password(),user.getPassword());
            if(isPasswordSame){
                return user;
            }
            throw new TwitterException("Invalid Credantials", HttpStatus.BAD_REQUEST);
        }
        throw new TwitterException("Invalid Credantials", HttpStatus.BAD_REQUEST);
    }
}
