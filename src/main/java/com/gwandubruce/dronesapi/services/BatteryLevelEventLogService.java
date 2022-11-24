package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.models.BatteryLevelEventLog;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.repositories.BatteryLevelEventLogRepository;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BatteryLevelEventLogService {

    private final BatteryLevelEventLogRepository batteryLevelEventLogRepository;
    private final DroneRepository droneRepository;

    @Scheduled(fixedRate = 60_000)
    public void monitorBatteryLevel() {

        List<BatteryLevelEventLog> batteryLevelEventLog = new ArrayList<>();
        Iterable<Drone> drones = droneRepository.findAll();

        drones.forEach(drone -> batteryLevelEventLog.add(
                BatteryLevelEventLog
                        .builder()
                        .logId(UUID.randomUUID().toString())
                        .batteryLevel(drone.getBatteryCapacityPercent())
                        .timeOfLogEntry(LocalDateTime.now())
                        .drone(drone)
                        .build()));

        batteryLevelEventLogRepository.saveAll(batteryLevelEventLog);
    }
}
