package com.lab.exceptions.cat;

public class NoSuchCatException extends RuntimeException{
    public NoSuchCatException(){}
    public NoSuchCatException(String message){
        super(message);
    }
}
