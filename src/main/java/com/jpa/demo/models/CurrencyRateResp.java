package com.jpa.demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class CurrencyRateResp {
    private String date;
    private String base;
    private Map<String, String> rates;
}
