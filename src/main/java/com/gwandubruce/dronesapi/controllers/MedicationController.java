package com.gwandubruce.dronesapi.controllers;

import com.gwandubruce.dronesapi.modelDTOs.MedicationDTO;
import com.gwandubruce.dronesapi.models.Medication;
import com.gwandubruce.dronesapi.services.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/medication")
public class MedicationController{

  private final MedicationService medicationService;

  public MedicationController(MedicationService medicationService) {
    this.medicationService = medicationService;
  }

  @ResponseBody
  @PostMapping(value = "/load-medication", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Medication> loadMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
    Medication response = medicationService.loadMedicationIntoDrone(medicationDTO);
    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @PostMapping(value = "/create")
  public ResponseEntity<Medication> add(@Valid @RequestBody MedicationDTO medicationDto) {
    Medication response = medicationService.addMedication(medicationDto);
    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @RequestMapping(value = "delete/{code}")
  public ResponseEntity<Boolean> delete(@PathVariable("code") String code) {
    Boolean response = medicationService.deleteMedicationByCode(code);
    return ResponseEntity.ok(response);
  }

}