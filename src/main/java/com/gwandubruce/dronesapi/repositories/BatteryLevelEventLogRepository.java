package com.gwandubruce.dronesapi.repositories;

import com.gwandubruce.dronesapi.models.BatteryLevelEventLog;
import org.springframework.data.repository.CrudRepository;

public interface BatteryLevelEventLogRepository extends CrudRepository<BatteryLevelEventLog, String> {
}
