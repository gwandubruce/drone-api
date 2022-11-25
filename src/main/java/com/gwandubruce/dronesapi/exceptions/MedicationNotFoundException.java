package com.gwandubruce.dronesapi.exceptions;

public class MedicationNotFoundException extends RuntimeException{

    public MedicationNotFoundException(){

        super("There are no medications in this drone");
    }
}
