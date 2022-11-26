package com.gwandubruce.dronesapi.exceptions;

public class BatteryLowException extends RuntimeException{

    public BatteryLowException(){

        super("You are not permitted to load this drone, Its Battery is too low!!!");
    }
}
