package com.example.sh.controller;

import com.example.sh.model.Vgd;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@RestController
public class VgdController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "vgds", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public Vgd setVgd(@RequestBody Vgd vgd) throws IOException {
        ByteArrayOutputStream vgdOut = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(vgdOut);
        os.writeObject(vgd);
        rabbitTemplate.convertAndSend("amq.topic", "actuators.vgds.vgd1", vgd);
        return vgd;
    }

}
