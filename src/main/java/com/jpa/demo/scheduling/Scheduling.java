package com.jpa.demo.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class Scheduling {
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(fixedRate = 10000)
    public void scheduled() {
        String url = "http://localhost:8080/kafka/send?currency=idr";
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET,
                null,
                String.class
        );
        System.out.println("Scheduled task at " + new Date() + ": " + response.getBody());
    }
}
