package com.gwandubruce.dronesapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    @Id
    private Long id;

    private String name;
    private String weight;
    private String code;
    private String image;

    @ManyToOne
    private Drone drone;
}
