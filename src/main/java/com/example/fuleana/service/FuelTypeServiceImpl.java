package com.example.fuleana.service;

import com.example.fuleana.entity.FuelType;
import com.example.fuleana.repository.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class FuelTypeServiceImpl implements FuelTypeService{

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelType getFuelTypeByName(String name) {
        String trimmedName = name.trim();
        FuelType fuelType = fuelTypeRepository.findByName(trimmedName)
                .orElseThrow(()->new EntityNotFoundException("FuelType Not Found with ID:" + trimmedName));
        return fuelType;
    }

}
