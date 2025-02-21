package com.jpa.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer_order_view_i") // Nama view di database
public class CustomerOrderView {
    @Id
    private String customer_name; // Bisa diubah sesuai kebutuhan kolom yang digunakan
    private String phone_number;
    private String address;
    private int total_amount;
    private String status;
}
