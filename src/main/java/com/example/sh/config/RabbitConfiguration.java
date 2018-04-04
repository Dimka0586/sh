package com.example.sh.config;

import com.example.sh.model.Vgd;
import com.example.sh.service.DeviceValueDayService;
import com.rabbitmq.client.Consumer;
import org.apache.commons.codec.Charsets;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.nio.cs.UnicodeEncoder;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.US_ASCII;


@EnableRabbit
@Configuration
public class RabbitConfiguration {

    @Autowired
    DeviceValueDayService deviceValueDayService;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
            connectionFactory.setUsername("home");
            connectionFactory.setPassword("home");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        //rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange amqTopic(){
        return new TopicExchange("amq.topic");
    }

    @Bean
    public SimpleMessageListenerContainer deviceListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    Float value = Float.parseFloat(new String(message.getBody()));
                    deviceValueDayService.addValueFromDevice(message.getMessageProperties().getConsumerQueue(), value);
                }catch (Exception e) {
                }
            }
        });

        return container;
    }

    /*@Bean
    public Queue vgdsQueue() {
        return new Queue("actuators.vgds.vgd1");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(vgdsQueue()).to(topicExchange()).with("actuators.vgds.vgd1");
    }*/


    /**@Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }**/


    /**
    @Bean
    public Queue tempTubeIn1Tep1Queue() {
        return new Queue("tempTubeIn1Tep1");
    }

    @Bean
    public Binding tempTubeIn1Tep1Binding(){
        return BindingBuilder.bind(tempTubeIn1Tep1Queue()).to(amqTopic())
                .with("sensors.teplica1.tempTubeIn1");
    }

    /*@RabbitListener(queues = "tempTubeIn1Tep1")
    public void worker1(String message) {
        System.out.println(message);
    }*/

    /**
    @Bean
    public Queue tempBathroomQueue() {
        return new Queue("tempBathroom");
    }

    @Bean
    public Binding tempBathroomBinding(){
        return BindingBuilder.bind(tempBathroomQueue()).to(topicExchange())
                .with("sensors.home.tempBathroom");
    }

    @RabbitListener(queues = "tempBathroom")
    public void tempBathroomWorker(String message) {
        StringBuilder builder = new StringBuilder(message.toCharArray().length);
        Arrays.stream(message.split(","))
                .forEach(el -> builder.append((char)Integer.parseInt(el)));
        deviceValueDayService.addValueFromDevice("_deviceId1", Float.parseFloat(builder.toString()));
    }
    */





}
