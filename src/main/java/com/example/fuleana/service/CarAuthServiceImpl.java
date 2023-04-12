package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.CarAuth;
import com.example.fuleana.entity.User;
import com.example.fuleana.entity.pk.CarAuthPk;
import com.example.fuleana.repository.CarAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CarAuthServiceImpl implements CarAuthService{

    @Autowired
    CarAuthRepository carAuthRepository;

    @Override
    public CarAuth createCarAuth(@NotNull User user, @NotNull Car car, final boolean isWrite, final boolean isDelete) {
        CarAuth carAuth = new CarAuth();
        carAuth.setCarAuthId(new CarAuthPk(user.getUserId(), car.getCarId()));
        carAuth.setUser(user);
        carAuth.setCar(car);
        carAuth.setWrite(isWrite);
        carAuth.setDelete(isDelete);
        return carAuth;
    }

    @Override
    public List<CarAuth> getCarAuthsByUser(@NotNull User user) {
        List<CarAuth> carAuths = carAuthRepository.findByUser(user);
        return carAuths;
    }

    @Override
    public List<CarAuth> getCarAuthsByCar(@NotNull Car car) {
        List<CarAuth> carAuths = carAuthRepository.findByCar(car);
        return carAuths;
    }

    @Override
    public CarAuth getCarAuthByUserAndCar(User user, Car car) {
        CarAuth carAuth = carAuthRepository.findById(new CarAuthPk(user.getUserId() , car.getCarId()))
                .orElseThrow(()-> new AccessDeniedException("You don't have the required permissions to access this resource."));
        return carAuth;
    }

    @Override
    public void validateHasAuth(@NotNull User user, @NotNull Car car) {
        CarAuth carAuth = carAuthRepository.findById(new CarAuthPk(user.getUserId() , car.getCarId()))
                .orElseThrow(()-> new AccessDeniedException("You don't have the required permissions to access this resource."));
    }

    @Override
    public void validateHasWrite(@NotNull CarAuth carAuth) {
        if(!carAuth.isWrite()){
            throw new AccessDeniedException("You don't have the required permissions to write this resource.");
        }
    }

    @Override
    public void validateHasDelete(@NotNull CarAuth carAuth) {
        if(!carAuth.isDelete()){
            throw new AccessDeniedException("You don't have the required permissions to delete this resource.");
        }
    }
}
