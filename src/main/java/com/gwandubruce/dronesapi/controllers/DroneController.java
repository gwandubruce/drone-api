package com.gwandubruce.dronesapi.controllers;

import com.gwandubruce.dronesapi.modelDTOs.DroneDTO;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.services.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drone")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @ResponseBody
    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<DroneDTO> register(@Valid @RequestBody DroneDTO drone) {
        DroneDTO response = droneService.registerDrone(drone);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping(value = "/check/medication/{serialNumber}", produces = "application/json")
    public ResponseEntity<List<Medication>> checkMedicationsOfADrone(@PathVariable("serialNumber") String serialNumber) {
        List<Medication> medications = droneService.checkMedicationsOfADrone(serialNumber);
        return ResponseEntity.ok(medications);
    }

    @ResponseBody
    @GetMapping(value = "/check/battery/{serialNumber}", produces = "application/json")
    public ResponseEntity<Double> checkBatteryLevelForADrone(@PathVariable("serialNumber") String serialNumber) {
        Double response = droneService.checkBatteryLevelForADrone(serialNumber);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping(value = "/available-drones", produces = "application/json")
    public ResponseEntity<List<String>> checkAvailableDrones() {
        List<String> response = droneService.checkSerialNumbersOfDronesAvailable();
        return ResponseEntity.ok(response);
    }
}