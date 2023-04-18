package com.example.fuleana.service;

import com.example.fuleana.entity.*;
import com.example.fuleana.repository.TripRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TripRecordServiceImpl implements TripRecordService{

    @Autowired
    TripRecordRepository tripRecordRepository;

    @Override
    public void createTripRecord(@NotNull Car car,@NotNull Purpose purpose, @NotNull FuelPrice fuelPrice,
                                 final float totalKilometers) {

        //使用燃料(L) = 走行距離(km) / 燃費(km/L)
        //かかった燃料費(円) = 使用燃料(L) * 1Lあたりの燃料費(円/L)
        final Float totalLiter = totalKilometers / car.getKilometersPerLiter();
        final Float totalYen = totalLiter * fuelPrice.getYenPerLiter();

        TripRecord tripRecord = new TripRecord();
        tripRecord.setCar(car);
        tripRecord.setPurpose(purpose);
        tripRecord.setTotalYen(totalYen);
        tripRecord.setTotalLiter(totalLiter);
        tripRecord.setTotalKilometers(totalKilometers);
        tripRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tripRecordRepository.save(tripRecord);

    }

    @Override
    public List<TripRecord> getTripRecordsByCarAndYearAndMonth(Car car,int year, int month ) {
        List<TripRecord> tripRecords = tripRecordRepository.findByCarAndYearAndMonth(car , year, month);
        return tripRecords;
    }

    @Override
    public TripRecordTotal getTripRecordTotalByCarAndYearAndMonth(Car car, int year, int month) {
        TripRecordTotal tripRecordTotal =
                tripRecordRepository.getTripRecordTotalByCarAndYearAndMonth(car, year, month)
                        .orElseThrow(()->new EntityNotFoundException("TripRecordTotal not found"));
        return tripRecordTotal;
    }
}
