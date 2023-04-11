package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.FuelType;
import com.example.fuleana.entity.User;

import java.util.List;

public interface CarService {

    void createCar(String discription ,FuelType fuelType, Float kilometersPerLiter);
    Car getCarByAlt(String altId);
    Car getCarByPk(Long carId);
    void updateDiscriptionByCar(Car car, String newDiscription);
    void updateFuelTypeByCar(Car car , FuelType newFuelType);
    void updateMileagePerLiterByCar(Car car, Float newMileagePerLiter);
    void deleteCarByCar(Car car);

}
