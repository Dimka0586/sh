package com.example.sh.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Document(collection = "analog_sensor_day")
public class AnalogSensorDay {

    @Id
    private long id;

    //@DateTimeFormat(pattern = "Y-M-D H:M:S")
    private Date date;

    private Calendar cal;

    private AnalogSensor analogSensor;

    private ArrayList<ArrayList<ArrayList<Float>>> val;



    //private float[][][] values;

    public AnalogSensorDay() {
        this.date = new Date();
        //this.values = new float[24][60][60];
        this.cal = Calendar.getInstance();
        this.cal.setTime(this.date);
        this.cal.set(Calendar.MILLISECOND, 0);
        this.val = new ArrayList<ArrayList<ArrayList<Float>>>();
    }

    public AnalogSensorDay(AnalogSensor analogSensor) {
        this.analogSensor = analogSensor;
        this.date = new Date();
        this.cal = Calendar.getInstance();
        this.cal.setTime(this.date);
        this.cal.set(Calendar.MILLISECOND, 0);
        //this.values = new float[24][60][60];
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public float[][][] getValues() {
        return values;
    }

    public void setValues(float[][][] values) {
        this.values = values;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AnalogSensor getAnalogSensor() {
        return analogSensor;
    }

    public void setAnalogSensor(AnalogSensor analogSensor) {
        this.analogSensor = analogSensor;
    }
}
