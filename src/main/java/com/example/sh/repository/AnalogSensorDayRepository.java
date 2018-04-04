package com.example.sh.repository;

import com.example.sh.model.AnalogSensorDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AnalogSensorDayRepository extends MongoRepository<AnalogSensorDay, Long> {

    /*AnalogSensorDay findAnalogSensorDayByDate_YearAndDate_MonthAndDate_DayAAndAnalogSensor_Name(
            int date_year, int date_month, int date_day, String sensor_name);*/
    // AnalogSensorDay findAnalogSensorDayByDateAndAnalogSensor_Name(Date date, String sensor_name);
    AnalogSensorDay findAnalogSensorDayByDate(Date date);

}
