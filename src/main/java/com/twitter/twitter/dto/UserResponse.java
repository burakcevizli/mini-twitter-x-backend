package com.twitter.twitter.dto;


public record UserResponse(String firstName, String lastName, String email , String password , String phone
        , String userName, String birthday, String registerDate , String address, String profilePicture) {
}
