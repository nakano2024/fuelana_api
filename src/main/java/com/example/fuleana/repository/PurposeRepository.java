package com.example.fuleana.repository;

import com.example.fuleana.entity.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurposeRepository extends JpaRepository<Purpose , Long> {

    Optional<Purpose> findByName(String name);

}
