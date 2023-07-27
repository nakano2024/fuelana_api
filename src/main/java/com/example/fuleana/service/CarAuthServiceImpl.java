package com.example.fuleana.service;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.CarAuth;
import com.example.fuleana.entity.User;
import com.example.fuleana.entity.pk.CarAuthPk;
import com.example.fuleana.repository.CarAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CarAuthServiceImpl implements CarAuthService{

    @Autowired
    CarAuthRepository carAuthRepository;

    @Override
    public CarAuth createCarAuth(@NotNull User user, @NotNull Car car, final boolean isWrite, final boolean isDelete) {

        CarAuth carAuth = new CarAuth();
        CarAuthPk carAuthPk = new CarAuthPk(user.getUserId(), car.getCarId());
        carAuth.setCarAuthId(carAuthPk);
        carAuth.setUser(user);
        carAuth.setCar(car);
        carAuth.setWrite(isWrite);
        carAuth.setDelete(isDelete);
        return carAuthRepository.save(carAuth);
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
        //権限がないユーザーに対しては、敢えてNot Foundを表示させる。認証エラーを出してリソースが存在することを伝えるよりも、
        //そもそも存在しないと表示させた方が安全である。
        CarAuth carAuth = carAuthRepository.findById(new CarAuthPk(user.getUserId() , car.getCarId()))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car Not Found with ID :" + car.getAltId()));
        return carAuth;
    }

    @Override
    public void validateHasAuth(@NotNull User user, @NotNull Car car) {
        //権限がないユーザーに対しては、敢えてNot Foundを表示させる。認証エラーを出してリソースが存在することを伝えるよりも、
        //そもそも存在しないと表示させた方が安全である。
        CarAuthPk carAuthPk = new CarAuthPk(user.getUserId() , car.getCarId());
        CarAuth carAuth = carAuthRepository.findById(carAuthPk)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car Not Found with ID :" + car.getAltId()));
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
