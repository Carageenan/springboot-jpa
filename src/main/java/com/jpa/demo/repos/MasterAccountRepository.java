package com.jpa.demo.repos;

import com.jpa.demo.models.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MasterAccountRepository extends JpaRepository<MasterAccount, UUID> {

    List<MasterAccount> findByUserId(UUID userId);
}
