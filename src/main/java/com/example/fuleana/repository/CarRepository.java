package com.example.fuleana.repository;

import com.example.fuleana.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car , Long> {

    Optional<Car> findByAltId(String altId);

}
