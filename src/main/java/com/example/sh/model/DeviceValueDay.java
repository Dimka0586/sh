package com.example.sh.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Document(collection = "device_value_day")
public class DeviceValueDay {
    private String _id;

    private String deviceId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    Map<Integer, Map<Integer, Map<Integer, Float>>> values;

    public DeviceValueDay() {
        this.date = new Date();
    }

    public DeviceValueDay(String deviceId, Float val) {
        this.deviceId = deviceId;
        this.values = new LinkedHashMap<>();
        Calendar cal = Calendar.getInstance();
        //cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.setTime(new Date());
        cal.set(Calendar.MILLISECOND, 0);
        this.date = cal.getTime();
        Map<Integer, Map> hourMap = new LinkedHashMap<>();
        Map<Integer, Map<Integer, Float>> minuteMap = new LinkedHashMap<>();
        Map<Integer, Float> secondMap = new LinkedHashMap<>();

        secondMap.put(cal.get(Calendar.SECOND), val);
        minuteMap.put(cal.get(Calendar.MINUTE), secondMap);
        this.values.put(cal.get(Calendar.HOUR_OF_DAY), minuteMap);


    }

    public DeviceValueDay(Date date, Map<Integer, Map<Integer, Map<Integer, Float>>> values) {
        this.date = date;
        this.values = values;
    }

    public Map<Integer, Map<Integer, Map<Integer, Float>>> getValues() {
        return values;
    }

    public void setValues(Map<Integer, Map<Integer, Map<Integer, Float>>> values) {
        this.values = values;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "DeviceValueDay{" +
                "_id='" + _id + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", date=" + date +
                ", values=" + values +
                '}';
    }
}
