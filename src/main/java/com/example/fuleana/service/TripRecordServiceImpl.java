package com.example.fuleana.service;

import com.example.fuleana.entity.*;
import com.example.fuleana.repository.TripRecordRepository;
import com.example.fuleana.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TripRecordServiceImpl implements TripRecordService{

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    TripRecordRepository tripRecordRepository;

    @Override
    public TripRecord getTripRecordByAlt(String altId) {
        final String trimmedAltId = altId;
        TripRecord tripRecord = tripRecordRepository.findByAltId(trimmedAltId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "TripRecord Not Found with ID : " + trimmedAltId));
        return tripRecord;
    }

    @Override
    public void createTripRecord(@NotNull Car car,@NotNull Purpose purpose, @NotNull FuelPrice fuelPrice,
                                 final float totalKilometers) {

        //使用燃料(L) = 走行距離(km) / 燃費(km/L)
        //かかった燃料費(円) = 使用燃料(L) * 1Lあたりの燃料費(円/L)
        final Float totalLiter = totalKilometers / car.getKilometersPerLiter();
        final Float totalYen = totalLiter * fuelPrice.getYenPerLiter();

        TripRecord tripRecord = new TripRecord();
        String altId = "";
        do {
            altId = idGenerator.generate();
        }while(tripRecordRepository.findByAltId(altId).orElse(null) != null);
        tripRecord.setAltId(altId);
        tripRecord.setCar(car);
        tripRecord.setPurpose(purpose);
        tripRecord.setTotalYen(totalYen);
        tripRecord.setTotalLiter(totalLiter);
        tripRecord.setTotalKilometers(totalKilometers);
        tripRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tripRecordRepository.save(tripRecord);

    }

    @Override
    public List<TripRecord> getTripRecordsByCarAndPurposeAndYearAndMonth(@NotNull Car car,String purposeName ,int year, int month ) {
        List<TripRecord> tripRecords = tripRecordRepository.findByCarAndPurposeAndYearAndMonth(car, purposeName , year, month);
        return tripRecords;
    }

    @Override
    public TripRecordsTotal getTripRecordsTotalByCarAndPurposeAndYearAndMonth(@NotNull Car car, String purposeName, int year, int month) {
        TripRecordsTotal tripRecordTotal =
                tripRecordRepository.getTripRecordsTotalByCarAndPurposeAndYearAndMonth(car, purposeName, year, month)
                        .orElseThrow(()->new EntityNotFoundException("TripRecordsTotal not found"));
        return tripRecordTotal;
    }

    @Override
    public TripRecordsTotal getTripRecordsTotalByCarAndPurposeAndYear(@NotNull Car car,String purposeName, int year) {
        TripRecordsTotal tripRecordsTotal = tripRecordRepository.getTripRecordsTotalByCarAndPurposeAndYear(car, purposeName, year)
                .orElseThrow(()->new EntityNotFoundException("TripRecordsTotal not found"));
        return tripRecordsTotal;
    }
}
