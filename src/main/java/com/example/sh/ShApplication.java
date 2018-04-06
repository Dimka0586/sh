package com.example.sh;

import com.example.sh.model.*;
import com.example.sh.repository.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.core.MongoTemplate;


import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class ShApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShApplication.class, args);
	}

	//@Autowired
	//AnalogSensorDayRepository analogSensorDayRepository;
	//@Autowired
	//AnalogSensorRepository analogSensorRepository;
	@Autowired
	DeviceValueDayRepository deviceValueDayRepository;
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	AmqpAdmin amqpAdmin;
	@Autowired
	TopicExchange amqTopic;
	@Autowired
	SimpleMessageListenerContainer simpleMessageListenerContainer;

	Map<String, Subscription> subscriptions = new HashMap<>();

	@PostConstruct
	public void init() {
		// home
		subscriptions.put("_tempBathroom",
				addDevice("_tempBathroom", "tempBathroom", "sensors.home.tempBathroom"));
		subscriptions.put("_tempBedroom",
				addDevice("_tempBedroom", "tempBedroom", "sensors.home.tempBedroom"));
		subscriptions.put("_tempEntrance",
				addDevice("_tempEntrance", "tempEntrance", "sensors.home.tempEntrance"));
		subscriptions.put("_tempKitchen",
				addDevice("_tempKitchen", "tempKitchen", "sensors.home.tempKitchen"));
		subscriptions.put("_tempKidsroom",
				addDevice("_tempKidsroom", "tempKidsroom", "sensors.home.tempKidsroom"));

		// teplica
		subscriptions.put("_tempAirTep1",
				addDevice("_tempAirTep1", "tempAirTep1", "sensors.teplica1.tempAir"));
		subscriptions.put("_humAirTep1",
				addDevice("_humAirTep1", "humAirTep1", "sensors.teplica1.humAir"));
		subscriptions.put("_tempTubeIn1Tep1",
				addDevice("_tempTubeIn1Tep1", "tempTubeIn1Tep1", "sensors.teplica1.tempTubeIn1"));
		subscriptions.put("_tempTubeOut1Tep1",
				addDevice("_tempTubeOut1Tep1", "tempTubeOut1Tep1", "sensors.teplica1.tempTubeOut1"));

		List<String> queueList = Arrays.asList(simpleMessageListenerContainer.getQueueNames());
		if (queueList.isEmpty()) {
			queueList = new ArrayList<>();
		}
		subscriptions.values().forEach(sub -> {
			Queue queue = new Queue(sub.getQueue());
			amqpAdmin.declareQueue(queue);
			amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(amqTopic)
					.with(sub.getRoutingKey()));
		});
		queueList.addAll(subscriptions.values().stream().map(el -> el.getQueue()).collect(Collectors.toList()));
		String[] queueArr = new String[queueList.size()];
		simpleMessageListenerContainer.setQueueNames(queueList.toArray(queueArr));


		//String[] queueArr = new String[queueList.size()];
		//simpleMessageListenerContainer.setQueueNames(queueList.toArray(queueArr));



	}

	private Subscription addDevice(String deviceId, String deviceName, String routingKey) {
		Device device = deviceRepository.findDeviceByDeviceId(deviceId);
		device = (device == null)
				? deviceRepository.save(new Device(deviceId, deviceName))
				: device;
		Subscription subscription = subscriptionRepository.findSubscriptionByDeviceId(deviceId);
		subscription = (subscription == null)
				? subscriptionRepository.save(new Subscription(deviceId,
				deviceId, routingKey))
				: subscription;
		return subscription;
	}

}
