package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.exceptions.DroneNotFoundException;
import com.gwandubruce.dronesapi.modelDTOs.MedicationDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import com.gwandubruce.dronesapi.repositories.MedicationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicationServiceTest {

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService = new MedicationService(medicationRepository, droneRepository);
    Drone drone;
    Medication medication;
    MedicationDTO medicationDTO;
    final String droneId = UUID.randomUUID().toString();
   final String medicationId = UUID.randomUUID().toString();

    @Before
   public  void setUp() {



//        drone =  new Drone(droneId, "A101015", 503, 98.0, Model.HEAVY_WEIGHT, State.IDLE, Collections.emptyList());

        medicationDTO = MedicationDTO.builder()
                .image("metformin.png")
                .name("Metformin")
                .weight(505.0)
                .code("CCFBYH")
                .build();

         medication = Medication.builder()
                .id(medicationId)
                .weight(medicationDTO.getWeight())
                .image(medicationDTO.getImage())
                .code(medicationDTO.getCode())
                .name(medicationDTO.getName())
                .build();
        List<Medication> medicationList = Arrays.asList(medication);


        drone = new Drone(droneId, "A101015", 503, 98.0, Model.HEAVY_WEIGHT, State.IDLE, medicationList);
        medication = drone.getMedicationList().get(0);

        medication.setDrone(drone);
        medicationDTO.setDrone(drone);
        medicationDTO.setId(medicationId);

        medicationRepository.save(medication);

    }

    @Test
   public void loadMedicationIntoDrone() {

        when(droneRepository.save(drone)).thenReturn(drone);
        Drone savedDrone = droneRepository.save(drone);


       medicationRepository.save(medication);


        Assert.assertThrows(DroneNotFoundException.class, () -> medicationService.loadMedicationIntoDrone(medicationDTO));
    }

}