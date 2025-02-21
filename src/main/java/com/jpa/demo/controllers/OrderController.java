package com.jpa.demo.controllers;

import com.jpa.demo.models.Customer;
import com.jpa.demo.models.CustomerOrderView;
import com.jpa.demo.models.User;
import com.jpa.demo.repos.CustomerOrderViewRepository;
import com.jpa.demo.repos.CustomerRepository;
import com.jpa.demo.repos.OrderRepository;
import com.jpa.demo.models.Order;
import com.jpa.demo.repos.UserRepository;
import com.jpa.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerOrderViewRepository customerOrderViewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("get")
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    @GetMapping("getView")
    public List<CustomerOrderView> getOrderView() {
        return customerOrderViewRepository.findAll();
    }

    @PostMapping("create")
    public Map<String, Object> createOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody Order order) {

        Map<String, Object> isTokenValid = jwtUtils.validateUser(token);
        if (isTokenValid.get("status").equals(false)) {
            return invalidTokenOutput(isTokenValid.get("msg").toString());
        }
        User user = (User) isTokenValid.get("user");
        Optional<Customer> customer = customerRepository.findByUserId(user.getUser_id());
        Date date = new Date(System.currentTimeMillis());

        Order orderSaved = new Order(customer.get().getCustomer_id(), date ,order.getTotal_amount(), "pending" );

        Order newOrder = orderRepository.save(orderSaved);
        if(newOrder != null) {
            return new HashMap<>(){{
                put("order", newOrder);
                put("status", "success");
            }};
        } else {
            return new HashMap<>(){{
                put("status", "Something went wrong");
            }};
        }
    }

    @PostMapping("edit")
    public Map<String, Object> editOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody Order order) {
        Map<String, Object> isTokenValid = jwtUtils.validateUser(token);
        if (isTokenValid.get("status").equals(false)) {
            return invalidTokenOutput(isTokenValid.get("msg").toString());
        }
        Order orderExist = orderRepository.findById(order.getOrder_id()).get();
        if(orderExist != null) {
            Order orderSaved = new Order(order.getOrder_id(), order.getCustomer_id(), order.getOrder_date(), order.getTotal_amount(), "pending");
            Order newOrder = orderRepository.save(orderSaved);
            if(newOrder != null) {
                return new HashMap<>(){{
                    put("order", newOrder);
                    put("status", "success");
                }};
                } else {
                return new HashMap<>(){{
                    put("status", "Something went wrong");
                }};
            }
        } else {
            return new HashMap<>(){{
                put("status", "Something went wrong");
            }};
        }
    }

    @PostMapping("delete/{id}")
    public Map<String, Object> deleteOrder(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        Map<String, Object> isTokenValid = jwtUtils.validateUser(token);
        if (isTokenValid.get("status").equals(false)) {
            return invalidTokenOutput(isTokenValid.get("msg").toString());
        }

        try {
            boolean isExist = orderRepository.existsById(id);
            if(!isExist) {
                return new HashMap<>(){{
                    put("status", "Error");
                    put("msg", "No such order");
                }};
            }
            orderRepository.deleteById(id);
            if(orderRepository.existsById(id)) {
                return new HashMap<>(){{
                    put("status", "error");
                    put("msg", "Order not deleted");
                }};
            } else {
                return new HashMap<>(){{
                    put("status", "Success");
                    put("msg", "order with id " + id + " has been deleted");
                }};
            }
        } catch (Exception e) {
            String[] parts = e.getMessage().split(":");
            String ErrorMsg = parts[0].trim();
            return new HashMap<>(){{
                put("status", "Something went wrong");
                put("msg", ErrorMsg);
            }};
        }
    }

    private Map<String,Object> invalidTokenOutput(String msg) {
        return new HashMap<String, Object>(){{
            put("message", msg);
        }};
    }
}
