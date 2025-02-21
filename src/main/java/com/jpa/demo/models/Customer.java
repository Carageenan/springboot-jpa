package com.jpa.demo.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "customers_i")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column(name = "user_id")
    private Long userId;
    private String customer_name;
    private String phone_number;
    private String address;
    public Customer() {}
    public Customer(Long userId, String customer_name, String phone_number, String address) {
        this.userId = userId;
        this.customer_name = customer_name;
        this.phone_number = phone_number;
        this.address = address;
    }
    public Customer(Long customer_id, Long userId, String customer_name, String phone_number, String address) {
        this.customer_id = customer_id;
        this.userId = Customer.this.userId;
        this.customer_name = customer_name;
        this.phone_number = phone_number;
        this.address = address;
    }
}
