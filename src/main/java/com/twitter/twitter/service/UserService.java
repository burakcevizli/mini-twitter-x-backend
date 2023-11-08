package com.twitter.twitter.service;



import com.twitter.twitter.entity.User;
import com.twitter.twitter.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.swing.plaf.OptionPaneUI;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers();

    User findByUserId(int id);

    User saveUser(User user);

    User deleteUser(int id);

   Optional<User> findUserByEmail(String email);

}
