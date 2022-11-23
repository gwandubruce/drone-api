package com.gwandubruce.dronesapi.repositories;

import com.gwandubruce.dronesapi.models.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication , Long> {
}
