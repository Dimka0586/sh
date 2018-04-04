package com.example.sh.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "analog_sensor_hour")
public class AnalogSensorHour {

    //@Id
    //private long id;

    private AnalogSensorMinute[] valuePerMinute;

    public AnalogSensorHour() {
        this.valuePerMinute = new AnalogSensorMinute[60];
    }

    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    public AnalogSensorMinute[] getValuePerMinute() {
        return valuePerMinute;
    }

    public void setValuePerMinute(AnalogSensorMinute[] valuePerMinute) {
        this.valuePerMinute = valuePerMinute;
    }
}
