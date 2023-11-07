package com.twitter.twitter.utils;

import com.twitter.twitter.dto.TweetResponse;
import com.twitter.twitter.dto.UserResponse;
import com.twitter.twitter.dto.UserTweetResponse;
import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
    public static UserResponse userResponseConverter(User user){
        return new UserResponse(user.getId(),user.getFirstName(),user.getLastName(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getUserName(), formatDate( user.getBirthday()),formatDate(user.getRegisterDate()) , user.getAddress(),
                user.getProfilePicture());
    }

    public static List<UserResponse> userResponseListConverter(List<User> userList){
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user : userList){
            userResponseList.add(userResponseConverter(user));
        }
        return userResponseList;
    }

    public static UserTweetResponse userTweetResponseConverter(UserResponse userResponse){
        return new UserTweetResponse(userResponse.id(), userResponse.firstName(), userResponse.lastName(),
                userResponse.profilePicture(), userResponse.userName());
    }

    public static List<UserTweetResponse> userTweetResponseListConverter(List<UserResponse> userResponseList){
        List<UserTweetResponse> userTweetResponseList = new ArrayList<>();
        for(UserResponse userResponse : userResponseList){
            userTweetResponseList.add(userTweetResponseConverter(userResponse));
        }
        return  userTweetResponseList;
    }

    public static TweetResponse tweetResponseConverter(Tweet tweet){
        return new TweetResponse(tweet.getId(), tweet.getText(),tweet.getLikes(),tweet.getRetweet(),tweet.getCommentsTotal(),
                formatDate(tweet.getTweetDate()) ,userTweetResponseConverter(userResponseConverter(tweet.getUser())));
    }

    public static List<TweetResponse> tweetResponseListConverter(List<Tweet> tweetList){
        List<TweetResponse> tweetResponseList = new ArrayList<>();
        for(Tweet tweet : tweetList){
            tweetResponseList.add(tweetResponseConverter(tweet));
        }
        return tweetResponseList;
    }


}
