package com.example.sh.repository;

import com.example.sh.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String>{

    Device findDeviceByName(String name);
    Device findDeviceByDeviceId(String deviceId);

}
