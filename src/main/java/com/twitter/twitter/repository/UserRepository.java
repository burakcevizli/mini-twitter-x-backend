package com.twitter.twitter.repository;

import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

}
