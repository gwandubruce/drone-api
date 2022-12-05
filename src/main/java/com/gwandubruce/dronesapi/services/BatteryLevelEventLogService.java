package com.gwandubruce.dronesapi.services;

import com.gwandubruce.dronesapi.models.BatteryLevelEventLog;
import com.gwandubruce.dronesapi.models.Drone;
import com.gwandubruce.dronesapi.repositories.BatteryLevelEventLogRepository;
import com.gwandubruce.dronesapi.repositories.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class BatteryLevelEventLogService {

    private final BatteryLevelEventLogRepository batteryLevelEventLogRepository;
    private final DroneRepository droneRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void monitorBatteryLevel() {

        log.info("Drone battery level audit log started");

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

        batteryLevelEventLog.stream()
                .filter(a -> a.getDrone() != null)
                .forEach(
                        lg -> log.info("The battery level of Drone with serial number ("
                                + lg.getDrone().getSerialNumber() + ") is "
                                + lg.getBatteryLevel() + "% charged as @ " + LocalDateTime.now()
                        ));
        log.info("Drone battery level audit log ended");

    }
}
