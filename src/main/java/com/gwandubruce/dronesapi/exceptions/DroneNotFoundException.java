package com.gwandubruce.dronesapi.exceptions;

public class DroneNotFoundException extends Exception{

    public DroneNotFoundException(){
        super("Could Not Find Such a Drone , Verify Your Drone Info!!!");
    }
    public DroneNotFoundException(String message){
        super(message);
    }
}
