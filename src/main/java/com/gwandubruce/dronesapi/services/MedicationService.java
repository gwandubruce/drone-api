package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.exceptions.*;
import com.gwandubruce.dronesapi.modelDTOs.MedicationDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import com.gwandubruce.dronesapi.repositories.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private DroneRepository droneRepository;

    public Medication loadMedicationIntoDrone (MedicationDTO medicationDTO ) throws MaximumWeightExceededException, DroneNotFoundException, UnloadableException, BatteryLowException {

        checkDroneLoadability(medicationDTO);

        Drone drone = droneRepository.findById(medicationDTO.getDrone().getId()).orElseThrow(()-> new DroneNotFoundException());

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
    private void checkDroneLoadability (MedicationDTO medicationDTO) throws DroneNotFoundException, UnloadableException, BatteryLowException, MaximumWeightExceededException {
        if (medicationDTO == null || medicationDTO.getDrone() == null) {
            return;
        }
        String droneId = medicationDTO.getDrone().getId();
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

    public Medication addMedication (MedicationDTO medication) {
        Optional<Medication> existingMedication = medicationRepository.findByCode(medication.getCode());
       if(existingMedication.isPresent()) {

           throw new DuplicateMedicationException();
       }
        Medication savedMedication = Medication.builder()
                        .id(UUID.randomUUID().toString())
                                .code(medication.getCode())
                                        .drone(medication.getDrone())
                                                .image(medication.getImage())
                                                        .name(medication.getName())
                                                                .weight(medication.getWeight())
                                                                        .build();

                return medicationRepository.save(savedMedication);
    }

    public Boolean deleteMedicationByCode (String code) {
        Optional<Medication> existingMedication = medicationRepository.findByCode(code);
       if(!existingMedication.isPresent()) {

           throw new MedicationNotFoundException();
       }
      return medicationRepository.deleteByCode(code);
    }

}
