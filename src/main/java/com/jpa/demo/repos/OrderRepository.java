package com.jpa.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa.demo.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
