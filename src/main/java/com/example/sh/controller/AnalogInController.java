package com.example.sh.controller;

import com.example.sh.model.AnalogIn;
import com.example.sh.repository.AnalogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController(value = "analogIns")
public class AnalogInController {



    //@Autowired
    //private AnalogInRepository analogInRepository;

    @PostMapping
    public AnalogIn createAnalogIn(AnalogIn analogIn) {
        return null;
        //return analogInRepository.saveAndFlush(analogIn);
    }

    @GetMapping
    public List<AnalogIn> getAnalogIns() {
        return null;
        //return analogInRepository.findAll();
    }


}
