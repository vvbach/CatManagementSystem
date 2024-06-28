package com.lab.exceptions.owner;

public class NoSuchOwnerException extends RuntimeException{
    public NoSuchOwnerException(){}
    public NoSuchOwnerException(String message){
        super(message);
    }
}
