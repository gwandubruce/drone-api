package com.gwandubruce.dronesapi.repositories;

import com.gwandubruce.dronesapi.models.Medication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends CrudRepository<Medication , String> {

    Optional<Medication> findByCode (String code);
    boolean deleteByCode(String code);
    List<Medication> findByDroneId(String droneId);
}
