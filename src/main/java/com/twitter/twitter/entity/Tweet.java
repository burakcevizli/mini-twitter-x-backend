package com.twitter.twitter.entity;

import com.twitter.twitter.exceptions.TwitterException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tweet",schema = "twitter")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotNull
    @Column(name = "text")
    private String text;


    @Column(name = "tweet_date")
    private LocalDate tweetDate;


    @Column(name = "liked_user_id")
    private List<Integer> likedUserIdList;

    @Column(name = "retweets_user_id")
    private List<Integer> retweetsUserIdList;

    @Column(name = "comments")
    private List<Integer> commentsTweetIdList;

    @Column(name = "commented_to")
    private Integer commentedTo;


    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private User user;

    public void addCommentsTweetIdList(int id){
        if(commentsTweetIdList == null){
            commentsTweetIdList = new ArrayList<>();
        }
        commentsTweetIdList.add(id);
    }
    public void removeCommentsTweetIdList(int id){
        if(commentsTweetIdList == null){
            throw new TwitterException("You didn't comment this tweet already.", HttpStatus.BAD_REQUEST);
        }
        if(commentsTweetIdList.contains(id)){
            commentsTweetIdList = commentsTweetIdList.stream().filter(eachId->eachId !=id).collect(Collectors.toList());
            return;
        }
        throw new TwitterException("You didn't comment this tweet already.", HttpStatus.BAD_REQUEST);
    }

    public void addRetweetsUserIdList(int id){
        if(retweetsUserIdList == null){
            retweetsUserIdList = new ArrayList<>();
        }
        if(retweetsUserIdList.contains(id)){
            throw new TwitterException("You already retweeted this tweet already ...",HttpStatus.BAD_REQUEST);
        }
        retweetsUserIdList.add(id);
    }
    public void removeRetweetsUserIdList(int id){
        if(retweetsUserIdList == null){
            throw new TwitterException("You didn't retweet this tweet already.", HttpStatus.BAD_REQUEST);
        }
        if(retweetsUserIdList.contains(id)){
            retweetsUserIdList = retweetsUserIdList.stream().filter(eachId->eachId !=id).collect(Collectors.toList());
            return;
        }
        throw new TwitterException("You didn't retweet this tweet already.", HttpStatus.BAD_REQUEST);
    }

    public void addLikedByUserList(int id){
        if(likedUserIdList == null){
            likedUserIdList = new ArrayList<>();
        }
        if(likedUserIdList.contains(id)){
            throw new TwitterException("You already liked this tweet already ...",HttpStatus.BAD_REQUEST);
        }
        likedUserIdList.add(id);
    }
    public void removeLikedByUserList(int id){
        if(likedUserIdList == null){
            throw new TwitterException("You didn't like this tweet already.", HttpStatus.BAD_REQUEST);
        }
        if(likedUserIdList.contains(id)){
         likedUserIdList = likedUserIdList.stream().filter(eachId->eachId !=id).collect(Collectors.toList());
         return;
        }
        throw new TwitterException("You didn't like this tweet already.", HttpStatus.BAD_REQUEST);
    }
}
