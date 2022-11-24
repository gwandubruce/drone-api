package com.gwandubruce.dronesapi.modelDTOs;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.models.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
public class DroneDTO {

    private String id;

    private String serialNumber;
    private Integer weightLimit;
    private Integer batteryCapacityPercent;


    private Model droneModel;

    private State state;

    private List<Medication> medicationList;


}
