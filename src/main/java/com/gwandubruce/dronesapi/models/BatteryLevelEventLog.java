package com.gwandubruce.dronesapi.models;

import lombok.AllArgsConstructor;
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
public class BatteryLevelEventLog {

    @Id
    private Long logId;

    @ManyToOne
    private Drone drone;

    private Integer batteryLevel;

    private LocalDateTime timeOfLogEntry;
}
