package com.example.sh.repository;

import com.example.sh.model.AnalogSensorHour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalogSensorHourRepository extends MongoRepository<AnalogSensorHour, Long> {
}
