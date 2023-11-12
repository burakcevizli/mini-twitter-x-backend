package com.twitter.twitter.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user",schema = "twitter")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String userName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "profile_wallpaper")
    private String profileWallpaper;

    @Column(name = "liked_tweets")
    private List<Integer> likedTweetsIds;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    List<Tweet> tweetList;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "followerUser")
    List<Follow> followerList;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "followingUser")
    List<Follow> followingList;

    public void addTweet(Tweet tweet){
        if(tweetList == null){
            tweetList = new ArrayList<>();
        }
        tweetList.add(tweet);
    }
    public void addLikedTweetsIds(int id){
        if(likedTweetsIds == null){
            likedTweetsIds = new ArrayList<>();
        }
        likedTweetsIds.add(id);
    }

    public void addFollower(Follow follow){
        if(followerList == null){
            followerList = new ArrayList<>();
        }
        followerList.add(follow);
    }

    public void addFollowing(Follow follow){
        if(followingList == null){
            followingList = new ArrayList<>();
        }
        followingList.add(follow);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
