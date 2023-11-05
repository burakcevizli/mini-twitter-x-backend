package com.twitter.twitter.controller;

import com.twitter.twitter.dto.TweetResponse;
import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.service.TweetService;
import com.twitter.twitter.utils.Converter;
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
    public List<TweetResponse> findAllTweets(){
        return Converter.tweetResponseListConverter(tweetService.findAllTweets());
    }

    @GetMapping("/{id}")
    public TweetResponse findTweetById(@PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.findTweetById(id));
    }

    @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet){
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet(@PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }



}
