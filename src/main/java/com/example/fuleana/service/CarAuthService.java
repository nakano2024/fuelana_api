package com.example.fuleana.service;


import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.CarAuth;
import com.example.fuleana.entity.User;

import java.util.List;

public interface CarAuthService {

    CarAuth createCarAuth(User user, Car car, boolean isWrite, boolean isDelete);
    List<CarAuth> getCarAuthsByUser(User user);
    List<CarAuth> getCarAuthsByCar(Car car);
    CarAuth getCarAuthByUserAndCar(User user, Car car);
    void validateHasAuth(User user , Car car);
    void validateHasWrite(CarAuth carAuth);
    void validateHasDelete(CarAuth carAuth);

}
