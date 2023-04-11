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
    public void validateUser(@NotNull User user, @NotNull Car car) {
        CarAuth carAuth = carAuthRepository.findById(new CarAuthPk(user.getUserId() , car.getCarId()))
                .orElseThrow(()-> new AccessDeniedException("You don't have the required permissions to access this resource."));
    }
}
