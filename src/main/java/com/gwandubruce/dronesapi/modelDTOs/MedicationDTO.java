package com.gwandubruce.dronesapi.modelDTOs;

import com.gwandubruce.dronesapi.models.Drone;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDTO {


    private String id;

    private String name;
    private Double weight;
    private String code;
    private String image;

    private Drone drone;
}
