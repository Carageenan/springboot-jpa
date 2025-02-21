package com.jpa.demo.controllers;

import com.jpa.demo.repos.CustomerRepository;
import com.jpa.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("get")
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }
}
