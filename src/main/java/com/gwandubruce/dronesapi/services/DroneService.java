package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.exceptions.DroneAlreadyExistsException;
import com.gwandubruce.dronesapi.exceptions.DroneModelDoesNotExist;
import com.gwandubruce.dronesapi.modelDTOs.DroneDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DroneService {

    private static final int DRONE_MAX_WEIGHT_LIMIT = 500;
    private static final int DRONE_MIN_BATTERY_LIMIT = 25;

    private static final List<State> LOADABLE_STATES = Arrays.asList(State.IDLE,State.LOADING);


    private final DroneRepository droneRepository;

    public DroneDTO registerDrone(DroneDTO droneDto) throws DroneAlreadyExistsException, DroneModelDoesNotExist {

        Optional<Drone> existingDrone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());

        if(existingDrone.isPresent()){

            throw new DroneAlreadyExistsException();
        }
        if(isWrongModel(droneDto)){

            throw new DroneModelDoesNotExist();
        }

        Drone newDrone = new Drone(UUID.randomUUID().toString(), droneDto.getSerialNumber(), droneDto.getWeightLimit(),
                droneDto.getBatteryCapacityPercent(), droneDto.getDroneModel(), State.IDLE, Collections.emptyList());

        Drone savedDrone = droneRepository.save(newDrone);

        return new DroneDTO(savedDrone.getId(), savedDrone.getSerialNumber(), savedDrone.getWeightLimit(),
                savedDrone.getBatteryCapacityPercent(), savedDrone.getDroneModel(), savedDrone.getState(),
                savedDrone.getMedicationList());
    }

    public List<String> checkSerialNumbersOfDronesAvailable() {

        List<Drone> drones = droneRepository.findByWeightLessThanAndBatteryGreaterThanAndStateIn(
                DRONE_MAX_WEIGHT_LIMIT,
                DRONE_MIN_BATTERY_LIMIT,
                LOADABLE_STATES);

      return   drones.stream()
              .map(Drone::getSerialNumber)
              .collect(Collectors.toList());
    }

    private boolean isWrongModel(DroneDTO drone){
        return !(drone.getDroneModel() instanceof Model);
    }
}
