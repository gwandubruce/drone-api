package com.gwandubruce.dronesapi.exceptions;

public class DroneModelDoesNotExist extends RuntimeException {
    public DroneModelDoesNotExist(String message) {
        super(message);
    }
    public DroneModelDoesNotExist(){
        super("No such Drone Model");
    }
}
