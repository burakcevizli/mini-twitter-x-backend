package com.twitter.twitter.controller;

import com.twitter.twitter.dto.TweetResponse;
import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.service.TweetService;
import com.twitter.twitter.service.UserService;
import com.twitter.twitter.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;
    private UserService userService;

    @Autowired
    public TweetController(TweetService tweetService,UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<TweetResponse> findAllTweets(){
        return Converter.tweetResponseListConverter(tweetService.findAllTweets());
    }

    @GetMapping("/{id}")
    public TweetResponse findTweetById(@PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.findTweetById(id));
    }

    @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet,@PathVariable int id){
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setId(id);
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet(@PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }



}
