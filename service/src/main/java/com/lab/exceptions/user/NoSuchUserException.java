package com.lab.exceptions.user;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(){}
    public NoSuchUserException(String message){
        super(message);
    }
}
