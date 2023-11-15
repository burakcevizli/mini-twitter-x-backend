package com.twitter.twitter.service;

import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.repository.TweetRepository;
import com.twitter.twitter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TweetServiceImplTest {

    private TweetService tweetService;


   @Mock
    private TweetRepository tweetRepository;

   @Mock
   private UserRepository userRepository;

   @BeforeEach
    void setUp(){
       tweetService = new TweetServiceImpl(tweetRepository,userRepository);
   }

   @Test
    void canSaveTweet(){
       Tweet tweet = new Tweet();
       User user = new User();
       tweet.setText("deneme123");
       tweet.setUser(user);
       tweetService.saveTweet(tweet);
       verify(tweetRepository).save(tweet);
   }

}
