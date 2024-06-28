package com.lab.exceptions.user;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(){}
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
