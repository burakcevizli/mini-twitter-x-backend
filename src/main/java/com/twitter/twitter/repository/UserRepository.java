package com.twitter.twitter.repository;


import com.twitter.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u.email FROM twitter.user AS u WHERE u.email =:email", nativeQuery = true)
     String emailChecker(String email);

    @Query(value = "SELECT u.phone FROM twitter.user AS u WHERE u.phone =:phone", nativeQuery = true)
    String phoneChecker(String phone);

    @Query(value = "SELECT u.username FROM twitter.user AS u WHERE u.username =:username", nativeQuery = true)
    String usernameChecker(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);



}
