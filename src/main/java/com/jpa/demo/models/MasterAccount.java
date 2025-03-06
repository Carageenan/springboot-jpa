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
@Table(name = "master_account_ihsan")

public class MasterAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    private int balance;
    public MasterAccount() {}
    public MasterAccount(UUID id, UUID userId, int balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }
    public MasterAccount(UUID userId, Integer balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
