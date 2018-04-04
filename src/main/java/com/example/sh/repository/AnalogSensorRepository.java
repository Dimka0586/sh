package com.example.sh.repository;

import com.example.sh.model.AnalogSensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalogSensorRepository extends MongoRepository<AnalogSensor, Long> {

    AnalogSensor findAnalogSensorByName(String name);

}
