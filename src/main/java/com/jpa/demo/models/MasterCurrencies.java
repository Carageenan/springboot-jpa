package com.jpa.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@Table(name = "CURRENCIES_MASTER_IHSAN")
public class MasterCurrencies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "TRANSACTION_DATE")
    private String date;
    private String base;
    private String rate;
    private String ratetype;
    public MasterCurrencies() {}
    public MasterCurrencies(UUID id, String date, String base, String rate, String ratetype) {
        this.id = id;
        this.date = date;
        this.base = base;
        this.rate = rate;
        this.ratetype = ratetype;
    }
    public MasterCurrencies(String date, String base, String rate, String ratetype) {
        this.date = date;
        this.base = base;
        this.rate = rate;
        this.ratetype = ratetype;
    }
}
