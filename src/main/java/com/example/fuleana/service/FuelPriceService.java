package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.FuelPrice;
import com.example.fuleana.entity.FuelType;

public interface FuelPriceService {

    FuelPrice getLatestFuelPriceByFuelType(FuelType fuelType);

}
