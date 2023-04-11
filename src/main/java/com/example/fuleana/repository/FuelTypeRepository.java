package com.example.fuleana.repository;

import com.example.fuleana.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType , Long> {

    Optional<FuelType> findByName(String name);

}
