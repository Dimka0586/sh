package com.example.sh.repository;

import com.example.sh.model.DeviceValueDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface DeviceValueDayRepository extends MongoRepository<DeviceValueDay, String>{

    DeviceValueDay findDeviceValueDayByDate(Date date);
}
