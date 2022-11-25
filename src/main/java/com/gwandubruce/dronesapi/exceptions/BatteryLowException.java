package com.gwandubruce.dronesapi.exceptions;

public class BatteryLowException extends Exception{

    public BatteryLowException(){

        super("You are not permitted to load this drone, Its Battery is too low!!!");
    }
}
