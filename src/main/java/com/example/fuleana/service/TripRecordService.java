package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.FuelPrice;
import com.example.fuleana.entity.Purpose;
import com.example.fuleana.entity.TripRecord;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TripRecordService {

    void createTripRecord(Car car, Purpose purpose, FuelPrice fuelPrice, Float totalKilometers );
    List<TripRecord> getTripRecordsByCarAndPurposeAndYear(Car car ,Purpose purpose ,Integer year);

}
