package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.FuelType;
import com.example.fuleana.repository.CarRepository;
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
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Autowired
    IdGenerator idGenerator;

    @Override
    public Car createCar(String discription,@NotNull FuelType fuelType, Float kilometersPerLiter) {
        Car car = new Car();
        String altId = "";
        do{
            altId = idGenerator.generate();
        }while (carRepository.findByAltId(altId.trim()).orElse(null) != null);
        car.setAltId(altId);
        car.setDiscription(discription);
        car.setFuelType(fuelType);
        car.setKilometersPerLiter(kilometersPerLiter);
        car.setDeleted(false);
        car.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return carRepository.save(car);
    }

    @Override
    public Car getCarByAlt(String altId) {
        final String trimmedAltId = altId.trim();
        Car car = carRepository.findByAltId(trimmedAltId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND , "Car Not Found with ID : " + trimmedAltId));
        return car;
    }

    @Override
    public Car getCarByPk(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(()->new EntityNotFoundException("Car Not Found with ID: " + carId));
        return car;
    }

    @Override
    public void updateDiscriptionByCar(@NotNull Car car, String newDiscription) {
        car.setDiscription(newDiscription);
        carRepository.save(car);
    }

    @Override
    public void updateFuelTypeByCar(@NotNull Car car, @NotNull FuelType newFuelType) {
        car.setFuelType(newFuelType);
        carRepository.save(car);
    }

    @Override
    public void updateMileagePerLiterByCar(@NotNull Car car, Float newKilometersPerLiter) {
        car.setKilometersPerLiter(newKilometersPerLiter);
        carRepository.save(car);
    }

    @Override
    public void deleteCarByCar(@NotNull Car car) {
        car.setDeleted(true);
        carRepository.save(car);
    }
}
