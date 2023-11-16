package com.twitter.twitter.repository;


import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TweetRepositoryTest {
    @Autowired
    private TweetRepository tweetRepository;
    @BeforeEach
    void setUp() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        createTweet("futureTweet", futureDate, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 6);
    }

    @Test
    void foundAllTweetsByFollowing() {
        List<Tweet> tweetList = tweetRepository.findAllTweetsByFollowing(6);
        assertEquals(1, tweetList.size());
        assertEquals("futureTweet", tweetList.get(0).getText());
    }
    private void createTweet(String text, LocalDate tweetDate, List<Integer> likedUserIdList,
                             List<Integer> retweetsUserIdList, List<Integer> commentsTweetIdList,
                             Integer commentedTo) {
        Tweet tweet = new Tweet();
        User user = new User();
        user.setFirstName("first name user");
        tweet.setText(text);
        tweet.setTweetDate(tweetDate);
        tweet.setLikedUserIdList(likedUserIdList);
        tweet.setRetweetsUserIdList(retweetsUserIdList);
        tweet.setCommentsTweetIdList(commentsTweetIdList);
        tweet.setCommentedTo(commentedTo);
        tweet.setUser(user);
        tweetRepository.save(tweet);
    }
}


