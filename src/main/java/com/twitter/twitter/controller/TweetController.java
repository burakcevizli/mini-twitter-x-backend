package com.twitter.twitter.controller;

import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/")
    public List<Tweet> findAllTweets(){
        return tweetService.findAllTweets();
    }

    @GetMapping("/{id}")
    public Tweet findTweetById(@PathVariable int id){
        return tweetService.findTweetById(id);
    }

    @PostMapping("/")
    public Tweet saveTweet(@RequestBody Tweet tweet){
        return tweetService.saveTweet(tweet);
    }

    @PutMapping("/{id}")
    public Tweet tweet(@RequestBody Tweet tweet){
        return tweetService.saveTweet(tweet);
    }

    @DeleteMapping("/{id}")
    public Tweet tweet(@PathVariable int id){
        return tweetService.deleteTweet(id);
    }



}
