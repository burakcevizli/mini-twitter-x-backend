package com.twitter.twitter.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tweet",schema = "twitter")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comments_total")
    private int commentsTotal;

    @Column(name = "retweet")
    private int retweet;

    @Column(name = "likes")
    private int likes;

    @Column(name = "text")
    private String text;

    @Column(name = "tweet_date")
    private Date tweetDate;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private User user;
}
