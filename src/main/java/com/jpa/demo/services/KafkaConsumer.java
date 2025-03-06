package com.jpa.demo.services;

import com.jpa.demo.event.KafkaMessageReceivedEvent;
import com.jpa.demo.models.MasterAccount;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumer {

    String currAPIKey = "20123d62a9f74fa6b7916e3ae8f31152";

    @KafkaListener(topics = "belajarKafka", groupId = "my-group-id")
    public void listen(String account) {

        System.out.println("masuk sini " + account);
        System.out.println("Received Message: " + account);
    }
    @KafkaListener(topics = "kafka2", groupId = "my-group-2")
    public void listen2(String message) {
        System.out.println("Received Message 2: " + message);
    }
}
