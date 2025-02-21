package com.jpa.demo.controllers;

import com.jpa.demo.repos.UserRepository;
import com.jpa.demo.models.LoginRequest;
import com.jpa.demo.models.User;
import com.jpa.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("get")
    public List<User> getUser() {
        return userRepository.findAll();
    }
    @PostMapping("login")
    public Map<String,Object> login(@RequestBody LoginRequest data) {
        Optional<User> foundUser = userRepository.findByUsername(data.getUsername());
        if (foundUser.isPresent()) {
            if(data.getPassword().equals(foundUser.get().getPassword())) {
                String token = jwtUtils.generateToken(data.getUsername());
                return new HashMap<String, Object>(){{
                    put("token", token);
                    put("user", foundUser.get().getUsername());
                }};
            } else {
                return new HashMap<String, Object>(){{
                    put("message", "Wrong password");
                }};
            }
        } else {
            return new HashMap<String, Object>(){{
                put("message", "User not found");
            }};
        }
    }

    @PostMapping("create")
    public Map<String,Object> createUser(@RequestBody User user) {
        userRepository.save(user);
        if(userRepository.findByUsername(user.getUsername()) != null) {
            return new HashMap<String, Object>(){{
                put("message", "Success created");
                put("user", userRepository.findByUsername(user.getUsername()));
            }};
        } else {
            return new HashMap<>(){{
                put("message", "Error create user");
            }};
        }
    }
}
