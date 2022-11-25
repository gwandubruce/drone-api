package com.gwandubruce.dronesapi.exceptions;

public class UnloadableException extends Exception{

    public UnloadableException(){

        super("You are not permitted to load this drone, It's unloadable!!!");
    }
}
