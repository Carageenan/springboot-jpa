package com.jpa.demo.repos;

import com.jpa.demo.models.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MasterUserRepository extends JpaRepository<MasterUser, UUID> {
}
