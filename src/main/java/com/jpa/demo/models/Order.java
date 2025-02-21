package com.jpa.demo.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "orders_i")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private Long customer_id;
    private Date order_date;
    private int total_amount;
    private String status;

    public Order() {}

    public Order(Long customer_id, Date order_date, int total_amount, String status) {
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }

    public Order(Long order_id, Long customer_id, Date order_date, int total_amount, String status) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }
}
