package com.twitter.twitter.dto;


import java.util.List;
import java.time.LocalDate;
import java.util.Date;

public record TweetResponse(Integer tweetId, String text  , List<Integer> commentsTweetIdList ,
                            String tweetDate, List<Integer> likedUserIdList,List<Integer> retweetsUserIdList , UserTweetResponse userTweetResponse){

}

