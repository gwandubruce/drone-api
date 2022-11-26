package com.gwandubruce.dronesapi.exceptions;

public class UnloadableException extends RuntimeException{

    public UnloadableException(){

        super("You are not permitted to load this drone, It's unloadable!!!");
    }
}
