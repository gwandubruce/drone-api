package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.modelDTOs.DroneDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;

    public DroneDTO registerDrone(DroneDTO drone) {

        Drone newDrone = new Drone(UUID.randomUUID().toString(), drone.getSerialNumber(), drone.getWeightLimit(),
                drone.getBatteryCapacityPercent(), drone.getDroneModel(), State.IDLE, Collections.emptyList());

        Drone savedDrone = droneRepository.save(newDrone);

        return new DroneDTO(savedDrone.getId(), savedDrone.getSerialNumber(), savedDrone.getWeightLimit(),
                savedDrone.getBatteryCapacityPercent(), savedDrone.getDroneModel(), savedDrone.getState(),
                savedDrone.getMedicationList());
    }
}
