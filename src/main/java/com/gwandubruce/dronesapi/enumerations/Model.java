package com.gwandubruce.dronesapi.enumerations;

public enum Model {
    LIGHT_WEIGHT("Light Weight"), MIDDLE_WEIGHT("Middle Weight"),
    CRUISER_WEIGHT("Cruiser Weight"), HEAVY_WEIGHT("Heavy Weight"), INVALID("Invalid Text");

    private String name;

    Model(String name) {

        this.name = name;
    }

    public static Model valueOfLabel (String name){

        for(Model m : values()){

            if(m.name.equalsIgnoreCase(name)){

                return m;
            }
        }

        return INVALID;
    }
}
