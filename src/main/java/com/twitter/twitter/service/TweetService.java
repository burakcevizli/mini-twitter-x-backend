package com.twitter.twitter.service;

import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;

import java.util.List;

public interface TweetService {
    List<Tweet> findAllTweetsByFollowing(int id);
    List<Tweet> findAllTweets();

    Tweet findTweetById(int id);

    Tweet saveTweet(Tweet tweet);

    Tweet deleteTweet(int id);

    List<Tweet> findTweetByUserId(int id);


}
