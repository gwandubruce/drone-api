package com.gwandubruce.dronesapi.preloaders;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import com.gwandubruce.dronesapi.repositories.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitialData {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    @PostConstruct
    private void postConstruct() {

       String id = UUID.randomUUID().toString();
        for(int i = 1; i < 20; i++){
            Drone drone = new Drone(id+i,"A10101"+""+i,500,98.0, Model.CRUISER_WEIGHT, State.IDLE, Collections.emptyList());
            Drone drone1 = new Drone(id+i,"A10101"+""+i,500,68.0, Model.HEAVY_WEIGHT, State.LOADING, Collections.emptyList());

            if(i%2 == 0) {

                droneRepository.save(drone);
            } else {

                droneRepository.save(drone1);
            }

            if(i%2 == 0) {
                medicationRepository.save(new Medication(id+i,"Metformin",50.0,"CCFBYH","metformin.png",drone));
            } else {

                medicationRepository.save(new Medication(id+i,"Metoprolol",90.0,"DCFBSH","metoprolol.png",drone1));
            }
        }
    }
}
