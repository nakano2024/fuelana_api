package com.example.fuleana.service;


import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.CarAuth;
import com.example.fuleana.entity.User;

import java.util.List;

public interface CarAuthService {

    List<CarAuth> getCarAuthsByUser(User user);
    List<CarAuth> getCarAuthsByCar(Car car);
    void validateUser(User user , Car car);

}
