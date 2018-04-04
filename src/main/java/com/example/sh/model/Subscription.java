package com.example.sh.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class Subscription {

    private String _id;
    private String deviceId;
    private String queue;
    private String routingKey;

    public Subscription() {
    }

    public Subscription(String deviceId, String queue, String routingKey) {
        this.deviceId = deviceId;
        this.queue = queue;
        this.routingKey = routingKey;
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

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "_id='" + _id + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", queue='" + queue + '\'' +
                ", routingKey='" + routingKey + '\'' +
                '}';
    }
}
