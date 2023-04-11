package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.FuelPrice;
import com.example.fuleana.entity.Purpose;
import com.example.fuleana.entity.TripRecord;
import com.example.fuleana.repository.TripRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class TripRecordServiceImpl implements TripRecordService{

    @Autowired
    TripRecordRepository tripRecordRepository;

    @Override
    public void createTripRecord(@NotNull Car car,@NotNull Purpose purpose, @NotNull FuelPrice fuelPrice,
                                 final Float totalKilometers) {

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
        tripRecordRepository.save(tripRecord);

    }

    @Override
    public List<TripRecord> getTripRecordsByCarAndPurposeAndYear(Car car, Purpose purpose, Integer year) {
        List<TripRecord> tripRecords = tripRecordRepository.findByCarAndPurposeAndYear(car, purpose , year);
        return tripRecords;
    }
}
