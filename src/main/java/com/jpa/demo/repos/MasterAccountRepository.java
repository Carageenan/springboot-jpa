package com.jpa.demo.repos;

import com.jpa.demo.models.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterAccountRepository extends JpaRepository<MasterAccount, Long> {

    List<MasterAccount> findByUserId(Long userId);
}
