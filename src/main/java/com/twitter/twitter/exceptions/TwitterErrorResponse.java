package com.twitter.twitter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TwitterErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
}
