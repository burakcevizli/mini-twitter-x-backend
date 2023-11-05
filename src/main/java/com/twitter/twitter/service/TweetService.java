package com.twitter.twitter.service;

import com.twitter.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> findAllTweets();

    Tweet findTweetById(int id);

    Tweet saveTweet(Tweet tweet);

    Tweet deleteTweet(int id);

    List<Tweet> findTweetByUserId(int id);
}
