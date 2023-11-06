package com.twitter.twitter.repository;


import com.twitter.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM user u WHERE u.email = :email", nativeQuery = true)
    public boolean emailChecker(String email);

}
