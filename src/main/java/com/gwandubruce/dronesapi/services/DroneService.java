package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.exceptions.DroneAlreadyExistsException;
import com.gwandubruce.dronesapi.exceptions.DroneModelDoesNotExist;
import com.gwandubruce.dronesapi.exceptions.MedicationNotFoundException;
import com.gwandubruce.dronesapi.modelDTOs.DroneDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DroneService {

    private static final int DRONE_MAX_WEIGHT_LIMIT = 500;
    private static final Double DRONE_MIN_BATTERY_LIMIT = 25.0;

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

        List<Drone> drones = droneRepository.findByWeightLimitLessThanAndBatteryCapacityPercentGreaterThanAndStateIn(
                DRONE_MAX_WEIGHT_LIMIT,
                DRONE_MIN_BATTERY_LIMIT,
                LOADABLE_STATES);

      return   drones.stream()
              .map(Drone::getSerialNumber)
              .collect(Collectors.toList());
    }

    public List<Medication> checkMedicationsOfADrone(String serialNumber) {
        List<Medication> medications = droneRepository.findMedicationListBySerialNumber(serialNumber);
        if (medications.size() == 0) {
            throw new MedicationNotFoundException();
        }
        return medications;
    }

    public Double checkBatteryLevelForADrone(String serialNumber) {
        Double batteryCapacityPercent = droneRepository.findBatteryCapacityPercentBySerialNumber(serialNumber);
        if (batteryCapacityPercent == null) {
            throw new IllegalArgumentException("Your serial Number could be wrong,Kindly re-check");
        }

        return batteryCapacityPercent;
    }


    private boolean isWrongModel(DroneDTO drone){
        return drone.getDroneModel().equals(Model.INVALID);
    }
}
