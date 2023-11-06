package com.twitter.twitter.service;

import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.exceptions.TwitterException;
import com.twitter.twitter.repository.TweetRepository;
import com.twitter.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        throw new TwitterException("id is not found ... ", HttpStatus.NOT_FOUND);
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        if(tweet.getText() == null){
            throw new TwitterException("Tweet texti boş olamaz ... ",HttpStatus.BAD_REQUEST);
        }
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

    @Override
    public List<Tweet> findTweetByUserId(int id) {
        List<Tweet> tweetList = tweetRepository.findTweetByUserId(id);
        if(!tweetList.isEmpty()){
            return tweetList;

        }
        throw new TwitterException("id si verilen kullanıcı tweeti yoktur. " , HttpStatus.NOT_FOUND);
    }


}
