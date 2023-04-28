package com.example.fuleana.service;

import com.example.fuleana.entity.*;

import java.util.List;

public interface TripRecordService {

    void createTripRecord(Car car, Purpose purpose, FuelPrice fuelPrice, float totalKilometers );

    List<TripRecord> getTripRecordsByCarAndPurposeAndYearAndMonth(Car car, String purposeName, int year, int month);

    TripRecordsTotal getTripRecordsTotalByCarAndPurposeAndYearAndMonth(Car car, String purposeName, int year, int month);

    TripRecordsTotal getTripRecordsTotalByCarAndPurposeAndYear(Car car, String purposeName, int year);

}
