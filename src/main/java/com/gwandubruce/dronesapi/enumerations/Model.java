package com.gwandubruce.dronesapi.enumerations;

public enum Model {
    LIGHT_WEIGHT("Light Weight"), MIDDLE_WEIGHT("Middle Weight"),
    CRUISER_WEIGHT("Cruiser Weight"), HEAVY_WEIGHT("Heavy Weight");

    private String name;

    Model(String name) {

        this.name = name;
    }
}
