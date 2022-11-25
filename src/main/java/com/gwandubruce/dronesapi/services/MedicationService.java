package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.exceptions.BatteryLowException;
import com.gwandubruce.dronesapi.exceptions.DroneNotFoundException;
import com.gwandubruce.dronesapi.exceptions.MaximumWeightExceededException;
import com.gwandubruce.dronesapi.exceptions.UnloadableException;
import com.gwandubruce.dronesapi.modelDTOs.MedicationDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import com.gwandubruce.dronesapi.repositories.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;

    public Medication loadMedicationIntoDrone (MedicationDTO medicationDTO , String droneId) throws MaximumWeightExceededException, DroneNotFoundException, UnloadableException, BatteryLowException {

        checkDroneLoadability(droneId,medicationDTO);

        Drone drone = droneRepository.findById(droneId).orElseThrow(()-> new DroneNotFoundException());

        Medication medication = Medication.builder()
                .id(UUID.randomUUID().toString())
                .image(medicationDTO.getImage())
                .name(medicationDTO.getName())
                .weight(medicationDTO.getWeight())
                .drone(drone)
                .build();

        drone.setState(State.LOADED);
        drone.getMedicationList().add(medication);
        droneRepository.save(drone);

        return medicationRepository.save(medication);
    }
    private void checkDroneLoadability (String droneId, MedicationDTO medicationDTO) throws DroneNotFoundException, UnloadableException, BatteryLowException, MaximumWeightExceededException {

        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        Drone drone = droneOptional.orElseThrow(()->new DroneNotFoundException());

        if(isUnloadable(drone)){
            throw new UnloadableException();
        }
        if(hasLowBattery(drone)){
            throw new BatteryLowException();
        }

        List<Medication> medications =  medicationRepository.findByDroneId(droneId);
        double totalWeight = medications
                .parallelStream()
                .mapToDouble(Medication::getWeight)
                .sum()
                + medicationDTO.getWeight();

        if(totalWeight > drone.getWeightLimit()){
            throw new MaximumWeightExceededException();
        }
    }
    private boolean isUnloadable(Drone drone){

        return !drone.getState().equals(State.IDLE) && !drone.getState().equals(State.LOADING);
    }
    private boolean hasLowBattery(Drone drone){
        return drone.getBatteryCapacityPercent() < 25;
    }



}
