package com.example.LPTBE1.exception;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message) {
        super(message);
    }
}
