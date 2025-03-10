package com.jpa.demo.services;

import com.jpa.demo.models.CurrencyRateResp;
import com.jpa.demo.models.MasterCurrencies;
import com.jpa.demo.repos.MasterCurrenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumer {
    @Autowired
    private MasterCurrenciesRepository masterCurrenciesRepository;

    @KafkaListener(topics = "belajarKafka", groupId = "my-group-id", containerFactory = "objectKafkaListenerContainerFactory")
    public void listen(CurrencyRateResp resp) {
        Map<String, String> rates = resp.getRates();
        String rateType = rates.keySet().iterator().next();
        String rate = rates.get(rateType);
        String date = resp.getDate();

        MasterCurrencies masterCurrencies = new MasterCurrencies(
                date,
                resp.getBase(),
                rate,
                rateType
        );
        masterCurrenciesRepository.save(masterCurrencies);
        System.out.println("Data berhasil disimpan: " + masterCurrencies);
    }
    @KafkaListener(topics = "kafka2", groupId = "my-group-2")
    public void listen2(String message) {
        System.out.println("Received Message 2: " + message);
    }
}
