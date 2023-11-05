package com.twitter.twitter.dto;


import java.util.Date;

public record TweetResponse(String text , Integer likes , Integer retweet , Integer commentsTotal ,
                            Date tweetDate, UserTweetResponse userTweetResponse){

}

