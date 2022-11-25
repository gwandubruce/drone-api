package com.gwandubruce.dronesapi.controllers;

import com.gwandubruce.dronesapi.modelDTOs.DroneDTO;
import com.gwandubruce.dronesapi.services.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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


}