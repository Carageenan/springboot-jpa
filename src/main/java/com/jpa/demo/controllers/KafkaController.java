package com.jpa.demo.controllers;

import com.jpa.demo.event.KafkaMessageReceivedEvent;
import com.jpa.demo.models.CurrencyRateResp;
import com.jpa.demo.models.MasterAccount;
import com.jpa.demo.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    RestTemplate restTemplate;
    @Value("${apiKeyCurrency}")
    String apiKey;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam("currency") String currency ) {
        System.out.println("nyampe api");
        String url = String.format("https://api.currencyfreaks.com/v2.0/rates/latest?apikey=%s&symbols=%s", apiKey, currency);
        System.out.println(url);
        ResponseEntity<CurrencyRateResp> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CurrencyRateResp>() {}
        );
        CurrencyRateResp currencyResp = response.getBody();
        System.out.println(currencyResp);

        kafkaProducer.sendMessage("belajarKafka", currencyResp);
        return "response kafka " + currency;
    }



}

