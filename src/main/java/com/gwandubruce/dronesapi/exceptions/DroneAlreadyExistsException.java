package com.gwandubruce.dronesapi.exceptions;

public class DroneAlreadyExistsException extends Exception {

    public DroneAlreadyExistsException() {
        super("Drone Already Exists");
    }

    public DroneAlreadyExistsException(String message) {
        super(message);
    }
}
