package com.gwandubruce.dronesapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication {

    @Id
    private String id;

    private String name;
    private Double weight;
    private String code;
    private String image;

    @ManyToOne
    private Drone drone;
}
