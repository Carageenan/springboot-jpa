package com.jpa.demo.controllers;

import com.jpa.demo.models.MasterAccount;
import com.jpa.demo.models.MasterUser;
import com.jpa.demo.repos.MasterAccountRepository;
import com.jpa.demo.repos.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("master")
public class MasterController {
    @Autowired
    private MasterUserRepository masterUserRepository;

    @Autowired
    private MasterAccountRepository masterAccountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("user")
    public List<MasterUser> getUser() {
        return masterUserRepository.findAll();
    }

    @GetMapping("account")
    public List<MasterAccount> getAccount() {
        return masterAccountRepository.findAll();
    }

    @PostMapping("account/create")
    public Map<String, Object> createAccount(@RequestBody MasterAccount masterAccount) {
        System.out.println(masterAccount + "ini");
        if(!masterUserRepository.existsById(masterAccount.getUserId())) {
            return new HashMap<>(){{
                put("message", "User not found");
            }};
        }
        MasterAccount isCreated = masterAccountRepository.save(masterAccount);
        if(isCreated == null) {
            return new HashMap<>(){{
                put("message", "something went wrong");
            }};
        } else {
            return new HashMap<>(){{
                put("message", "Account created");
                put("account", isCreated);
            }};
        }
    }

    @GetMapping("user/check/{id}")
    public Map<String, Object> checkUser(@PathVariable UUID id) {
        if(masterUserRepository.existsById(id)) {
            return new HashMap<>(){{
                put("msg", "account exists");
                put("user", masterUserRepository.findById(id).get());
            }};
        } else {
            return new HashMap<>(){{
                put("msg", "account does not exist");
            }};
        }
    }

    @GetMapping("user/balance/{id}")
    public Map<String, Object> getBalance(@PathVariable UUID id) {
        if(!masterUserRepository.existsById(id)) {
            return new HashMap<>(){{
                put("msg", "user does not exist");
            }};
        }
        List<MasterAccount> userAccount = masterAccountRepository.findByUserId(id);
        if(userAccount.size() == 0) {
            return new HashMap<>(){{
                put("msg", "user does not have account");
            }};
        }
        int totalBalance = userAccount
                .stream()
                .mapToInt(MasterAccount::getBalance)
                .sum();
        return new HashMap<>(){{
            put("balance", totalBalance);
            put("msg", "Success");
        }};
    }

    @GetMapping("user/balanceJDBC/{id}")
    public ResponseEntity<Map<String, Object>> getBalanceJDBC(@PathVariable String id) {
        System.out.println(id + " ini");
        byte[] convertedId = convertUuidToRaw(UUID.fromString(id));
        try {
            String sql = "select SUM(balance) from master_account_ihsan where user_id = ?";
            Integer totalBalance = jdbcTemplate.queryForObject(sql, new Object[]{convertedId}, Integer.class);
            if(totalBalance == null) throw new Exception();
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>(){{
                put("balance", totalBalance);
                put("msg", "Success");
            }});
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new HashMap<>(){{
                put("msg", "Something went wrong: " + e.getMessage());
            }});
        }
    }

    public byte[] convertUuidToRaw(UUID uuid) {
        String uuidStr = uuid.toString().replace("-", "");
        return hexStringToByteArray(uuidStr);
    }

    // Helper method to convert hex string to byte array
    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


}
