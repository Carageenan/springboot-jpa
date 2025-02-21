package com.jpa.demo.repos;

import com.jpa.demo.models.CustomerOrderView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderViewRepository extends JpaRepository<CustomerOrderView, String> {
}
