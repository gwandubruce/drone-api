package com.gwandubruce.dronesapi.modelDTOs;

import com.gwandubruce.dronesapi.models.Drone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Value
public class MedicationDTO {


    private String id;

    private String name;
    private String weight;
    private String code;
    private String image;

    private Drone drone;
}
