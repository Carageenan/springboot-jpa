package com.jpa.demo.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "master_account")

public class MasterAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private int balance;
    public MasterAccount() {}
    public MasterAccount(Long id, Long userId, int balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }
    public MasterAccount(Long userId, Integer balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
