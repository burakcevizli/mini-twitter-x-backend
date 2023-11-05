package com.twitter.twitter.dto;

import java.util.Date;

public record UserResponse(String firstName, String lastName,String email , String password , String phone
        ,String userName,Date birthday,Date registerDate ,String address, String profilePicture) {
}
