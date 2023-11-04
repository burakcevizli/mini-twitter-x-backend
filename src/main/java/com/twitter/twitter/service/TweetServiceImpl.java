package com.twitter.twitter.service;

import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.repository.TweetRepository;
import com.twitter.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tweet> findAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet findTweetById(int id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if(optionalTweet.isPresent()){
            return optionalTweet.get();
        }
        return null; //TODO Exception eklenecek...
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        User user = tweet.getUser();
        user.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet deleteTweet(int id) {
        Tweet tweet = findTweetById(id);
        tweetRepository.delete(tweet);
        return tweet;
    }
}
