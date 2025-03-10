package com.jpa.demo.repos;

import com.jpa.demo.models.MasterCurrencies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MasterCurrenciesRepository extends JpaRepository<MasterCurrencies, UUID> {
}
