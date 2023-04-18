package com.example.fuleana.service;

import com.example.fuleana.entity.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TripRecordService {

    void createTripRecord(Car car, Purpose purpose, FuelPrice fuelPrice, float totalKilometers );
    List<TripRecord> getTripRecordsByCarAndYearAndMonth(Car car, int year, int month);
    TripRecordTotal getTripRecordTotalByCarAndYearAndMonth(Car car, int year, int month);

}
