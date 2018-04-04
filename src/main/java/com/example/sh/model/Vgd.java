package com.example.sh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Vgd implements Serializable{

    private String name;
    private boolean onn;
    private boolean off;
    private boolean on;
    @JsonIgnore
    private String offLabel;
    @JsonIgnore
    private String onnLabel;

    public Vgd() {
    }

    public Vgd(String name, boolean off, String offLabel, boolean on, boolean onn, String onnLabel) {
        this.name = name;
        this.onn = onn;
        this.off = off;
        this.on = on;
        this.offLabel = offLabel;
        this.onnLabel = onnLabel;
    }

    public void handleMessage(Vgd vgd) {
        System.out.println(vgd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnn() {
        return onn;
    }

    public void setOnn(boolean onn) {
        this.onn = onn;
    }

    public boolean isOff() {
        return off;
    }

    public void setOff(boolean off) {
        this.off = off;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getOffLabel() {
        return offLabel;
    }

    public void setOffLabel(String offLabel) {
        this.offLabel = offLabel;
    }

    public String getOnnLabel() {
        return onnLabel;
    }

    public void setOnnLabel(String onnLabel) {
        this.onnLabel = onnLabel;
    }

    @Override
    public String toString() {
        return "Vgd{" +
                "name='" + name + '\'' +
                ", onn=" + onn +
                ", off=" + off +
                ", on=" + on +
                ", offLabel='" + offLabel + '\'' +
                ", onnLabel='" + onnLabel + '\'' +
                '}';
    }
}
