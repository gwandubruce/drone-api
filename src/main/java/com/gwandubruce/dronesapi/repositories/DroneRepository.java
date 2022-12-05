package com.gwandubruce.dronesapi.repositories;

import com.gwandubruce.dronesapi.enumerations.State;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.models.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DroneRepository extends CrudRepository<Drone , String> {

    Optional<Drone> findBySerialNumber(String serialNumber);
    List<Drone> findByWeightLimitLessThanAndBatteryCapacityPercentGreaterThanAndStateIn(Integer weight, Double batteryPercent, List<State> state);

    @Query("select d.batteryCapacityPercent from Drone d where d.serialNumber = ?1")
    Double findBatteryCapacityPercentBySerialNumber(String serialNumber);

    @Query("select d.medicationList from Drone d where d.serialNumber = ?1")
    List<Medication> findMedicationListBySerialNumber(String serialNumber);
}
