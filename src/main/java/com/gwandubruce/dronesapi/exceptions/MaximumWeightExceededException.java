package com.gwandubruce.dronesapi.exceptions;

public class MaximumWeightExceededException extends Exception{

    public MaximumWeightExceededException(){

        super("You are not permitted to load this drone, You exceeded the maximum drone weight!!!");
    }
}
