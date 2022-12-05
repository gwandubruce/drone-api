package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.enumerations.Model;
import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.exceptions.DroneModelDoesNotExist;
import com.gwandubruce.dronesapi.modelDTOs.DroneDTO;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DroneServiceTest {

    private static int DRONE_MAX_WEIGHT_LIMIT;
    private static Double DRONE_MIN_BATTERY_LIMIT;
    private static List<State> LOADABLE_STATES;

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneService droneService = new DroneService(droneRepository);

    Drone drone;
    Optional<Drone> optionalDrone;

    @Before
    public void setUp() {
        DRONE_MAX_WEIGHT_LIMIT = 500;
        DRONE_MIN_BATTERY_LIMIT = 25.0;
        LOADABLE_STATES = Arrays.asList(State.IDLE, State.LOADING);

        drone = new Drone(UUID.randomUUID().toString(), "A101012", 500, 98.0, Model.valueOfLabel("Heavy Weight"), State.IDLE, Collections.emptyList());
        optionalDrone = Optional.of(drone);


    }

    @Test
    public void register_drone() {

        DroneDTO droneDto = new DroneDTO(UUID.randomUUID().toString(), "A101012", 500, 98.0, Model.HEAVY_WEIGHT, State.IDLE, Collections.emptyList());
        Drone drone = Drone.builder()
                .id(droneDto.getId())
                .serialNumber(droneDto.getSerialNumber())
                .weightLimit(droneDto.getWeightLimit())
                .batteryCapacityPercent(droneDto.getBatteryCapacityPercent())
                .droneModel(droneDto.getDroneModel())
                .state(droneDto.getState())
                .medicationList(droneDto.getMedicationList())
                .build();

        when(droneRepository.save(drone)).thenReturn(drone);

        Drone savedDrone = droneRepository.save(drone);

        Assert.assertEquals("Drone Not Registered", true, drone.equals(savedDrone));

    }

    @Test
    public void generate_exception_on_registering_drone_with_wrong_model() {

        DroneDTO droneDto = new DroneDTO(UUID.randomUUID().toString(), "A101015", 500, 98.0, Model.INVALID, State.IDLE, Collections.emptyList());

        Assert.assertThrows(DroneModelDoesNotExist.class, () -> droneService.registerDrone(droneDto));
    }

    @Test
   public void checkSerialNumbersOfDronesAvailable() {
    }

    @Test
   public void checkMedicationsOfADrone() {
    }

    @Test
   public void checkBatteryLevelForADrone() {
    }
}