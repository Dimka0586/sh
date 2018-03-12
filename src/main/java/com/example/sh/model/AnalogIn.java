package com.example.sh.model;

import javax.persistence.*;

@Entity
public class AnalogIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "value")
    Integer value;

    public AnalogIn() {
    }

    public AnalogIn(Integer value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
