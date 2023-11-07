package com.twitter.twitter.dto;

import java.util.Date;

public record UserTweetResponse(Integer id,String firstName, String lastName, String profilePicture, String userName) {
}
