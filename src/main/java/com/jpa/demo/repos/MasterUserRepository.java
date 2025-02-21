package com.jpa.demo.repos;

import com.jpa.demo.models.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterUserRepository extends JpaRepository<MasterUser, Long> {
}
