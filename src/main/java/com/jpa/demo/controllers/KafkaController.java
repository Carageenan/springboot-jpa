package com.jpa.demo.controllers;

import com.jpa.demo.event.KafkaMessageReceivedEvent;
import com.jpa.demo.models.MasterAccount;
import com.jpa.demo.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    RestTemplate restTemplate;

    String apiKey = "20123d62a9f74fa6b7916e3ae8f31152";

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam("currency") String currency ) {
        System.out.println("nyampe api");
        kafkaProducer.sendMessage("belajarKafka", currency);
        return "response kafka " + currency;
    }


}

