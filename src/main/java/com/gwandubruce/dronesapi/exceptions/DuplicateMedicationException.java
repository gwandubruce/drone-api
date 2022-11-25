package com.gwandubruce.dronesapi.exceptions;

public class DuplicateMedicationException extends RuntimeException{

    public DuplicateMedicationException(){

        super("Medication already created");
    }
}
