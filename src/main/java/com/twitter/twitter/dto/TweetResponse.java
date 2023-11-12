package com.twitter.twitter.dto;


import java.util.List;
import java.time.LocalDate;
import java.util.Date;

public record TweetResponse(Integer tweetId, String text , Integer retweet , Integer commentsTotal ,
                            String tweetDate, List<Integer> likedUserIdList , UserTweetResponse userTweetResponse){

}

