package com.gwandubruce.dronesapi.repositories;

import com.gwandubruce.dronesapi.models.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone , Long> {
}
