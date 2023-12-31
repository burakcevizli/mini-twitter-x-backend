package com.twitter.twitter.controller;

import com.twitter.twitter.dto.TweetResponse;
import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.service.TweetService;
import com.twitter.twitter.service.UserService;
import com.twitter.twitter.utils.Converter;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/tweet")
@Validated
public class TweetController {

    private TweetService tweetService;
    private UserService userService;

    @Autowired
    public TweetController(TweetService tweetService,UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping("/homepage/{id}")
    public List<TweetResponse> findAllTweetsByFollowing(@Positive @PathVariable int id){
        return Converter.tweetResponseListConverter(tweetService.findAllTweetsByFollowing(id));
    }

    @GetMapping("/profile/{userId}")
    public List<TweetResponse> findAllTweetsByUserId(@Positive @PathVariable int userId){
        return Converter.tweetResponseListConverter(tweetService.findTweetByUserId(userId));
    }


    @GetMapping("/{id}")
    public TweetResponse findTweetById(@Positive @PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.findTweetById(id));
    }

    @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setCommentedTo(0);
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }


    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet,@Positive @PathVariable int id){
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setId(id);
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet(@Positive @PathVariable int id){
        Tweet tweet = tweetService.findTweetById(id);

       if(tweet.getCommentsTweetIdList() == null ){
           tweet.setCommentsTweetIdList(new ArrayList<>());
           tweet.setCommentedTo(0);
           tweetService.saveTweet(tweet);
       }

        if( !(tweet.getCommentsTweetIdList().isEmpty())){

            for(int i : tweet.getCommentsTweetIdList()){
                Tweet tweet3 = tweetService.findTweetById(i);
                tweet3.setCommentedTo(0);
                tweetService.saveTweet(tweet3);

            }
            tweet.setCommentsTweetIdList(new ArrayList<>());
            tweet.setCommentedTo(0);
            tweetService.saveTweet(tweet);
        }
        if(tweet.getCommentedTo() != 0){
           Tweet tweet1 = tweetService.findTweetById(tweet.getCommentedTo());
           tweet1.removeCommentsTweetIdList(id);
        }

        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }

    @PostMapping("/like/{id}")
    public TweetResponse likeTweet(@Positive @PathVariable int id,@RequestBody User user){
        Tweet tweet = tweetService.findTweetById(id);
        User user1 = userService.findByUserId(user.getId());
        user1.addLikedTweet(tweet.getId());
        tweet.addLikedByUserList(user1.getId());
        tweetService.saveTweet(tweet);
        return Converter.tweetResponseConverter(tweet);
    }


    @PostMapping("/dislike/{id}")
    public TweetResponse deleteTweet(@Positive @PathVariable int id , @RequestBody User user){
        Tweet tweet = tweetService.findTweetById(id);
        User user1 = userService.findByUserId(user.getId());
        tweet.removeLikedByUserList(user1.getId());
        user1.removeLikedTweet(tweet.getId());
        tweetService.saveTweet(tweet);
        return Converter.tweetResponseConverter(tweet);
    }

    @PostMapping("/retweet/{id}")
    public TweetResponse retweetTweet(@Positive @PathVariable int id,@RequestBody User user){
        Tweet tweet = tweetService.findTweetById(id);
        User user1 = userService.findByUserId(user.getId());
        user1.addRetweetsTweetsIdList(tweet.getId());
        tweet.addRetweetsUserIdList(user1.getId());
        tweetService.saveTweet(tweet);
        return Converter.tweetResponseConverter(tweet);
    }

    @PostMapping("/unretweet/{id}")
    public TweetResponse unRetweet(@Positive @PathVariable int id , @RequestBody User user){
        Tweet tweet = tweetService.findTweetById(id);
        User user1 = userService.findByUserId(user.getId());
        tweet.removeRetweetsUserIdList(user1.getId());
        user1.removeRetweetsTweetsIdList(tweet.getId());
        tweetService.saveTweet(tweet);
        return Converter.tweetResponseConverter(tweet);
    }
    @PostMapping("/reply/{id}")
    public TweetResponse addComment(@Positive @PathVariable int id , @RequestBody Tweet tweet){
        Tweet commentedTweet = tweetService.findTweetById(id);
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setUser(user);
        tweet.setCommentedTo(id);
        Tweet tweet1 = tweetService.saveTweet(tweet);
        commentedTweet.addCommentsTweetIdList(tweet1.getId());
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet1));
    }

    @DeleteMapping("/reply/{id}")
    public TweetResponse deleteComment(@Positive @PathVariable int id){
        Tweet tweet = tweetService.findTweetById(id);
        Tweet tweet1 = tweetService.findTweetById(tweet.getCommentedTo());
        tweet1.removeCommentsTweetIdList(id);
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }


}
