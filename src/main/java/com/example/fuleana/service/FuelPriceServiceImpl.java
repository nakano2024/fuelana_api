package com.example.fuleana.service;

import com.example.fuleana.entity.FuelPrice;
import com.example.fuleana.entity.FuelType;
import com.example.fuleana.repository.FuelPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class FuelPriceServiceImpl implements FuelPriceService{

    @Autowired
    FuelPriceRepository fuelPriceRepository;

    @Override
    public FuelPrice getLatestFuelPriceByFuelType(@NotNull FuelType fuelType) {
        FuelPrice fuelPrice = fuelPriceRepository.findFirstByFuelTypeOrderByCreatedAtDesc(fuelType)
                .orElseThrow(()->new EntityNotFoundException("FuelPrice Not Found with FuelType"));
        return fuelPrice;
    }

}
