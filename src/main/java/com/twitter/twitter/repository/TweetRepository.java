package com.twitter.twitter.repository;

import com.twitter.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Integer> {
    @Query("SELECT t FROM Tweet t " +
            "INNER JOIN t.user u " +
            "WHERE u.id = :id ORDER BY t.id DESC")
    public List<Tweet> findTweetByUserId( int id);

    @Query("SELECT t FROM Tweet t ORDER BY t.id DESC")
    public List<Tweet> findAllTweetsByFollowing(int id);


}
