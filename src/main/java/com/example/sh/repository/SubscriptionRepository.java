package com.example.sh.repository;

import com.example.sh.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, String>{

    Subscription findSubscriptionByDeviceId(String deviceId);
}
