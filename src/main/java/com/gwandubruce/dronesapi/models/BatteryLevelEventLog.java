package com.gwandubruce.dronesapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatteryLevelEventLog {

    @Id
    private String logId;

    @ManyToOne
    private Drone drone;

    private Double batteryLevel;

    private LocalDateTime timeOfLogEntry;
}
