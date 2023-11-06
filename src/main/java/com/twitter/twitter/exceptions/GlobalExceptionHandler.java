package com.twitter.twitter.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                twitterException.getHttpStatus().value(), twitterException.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(twitterErrorResponse, twitterException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now()
        );
        log.error("Exception occured ... " + exception.getMessage());
        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
