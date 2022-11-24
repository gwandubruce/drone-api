package com.gwandubruce.dronesapi.models;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {

    @Id
    private String id;

    private String serialNumber;
    private Integer weightLimit;
    private Integer batteryCapacityPercent;

    @Enumerated(EnumType.STRING)
    private Model droneModel;
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany
    private List<Medication> medicationList;


}
