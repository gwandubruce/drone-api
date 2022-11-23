package com.gwandubruce.dronesapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Medication {

    @Id
    private Long id;
    private String name;
    private String weight;
    private String code;
    private String image;
}
