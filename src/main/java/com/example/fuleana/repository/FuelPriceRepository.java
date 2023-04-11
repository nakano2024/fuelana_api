package com.example.fuleana.repository;

import com.example.fuleana.entity.FuelPrice;
import com.example.fuleana.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelPriceRepository extends JpaRepository<FuelPrice , Long> {

    Optional<FuelPrice> findFirstByFuelTypeOrderByCreatedAtDesc(FuelType fuelType);

}
