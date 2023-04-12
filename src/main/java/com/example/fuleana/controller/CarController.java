package com.example.fuleana.controller;

import com.example.fuleana.entity.Car;
import com.example.fuleana.entity.CarAuth;
import com.example.fuleana.entity.FuelType;
import com.example.fuleana.entity.User;
import com.example.fuleana.request.CarRequest;
import com.example.fuleana.response.CarDetailsResponse;
import com.example.fuleana.response.CarIndexResponse;
import com.example.fuleana.response.CreatedCarResponse;
import com.example.fuleana.service.CarAuthService;
import com.example.fuleana.service.CarService;
import com.example.fuleana.service.FuelTypeService;
import com.example.fuleana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired
    UserService us;

    @Autowired
    CarAuthService cas;

    @Autowired
    CarService cs;

    @Autowired
    FuelTypeService fts;

    @PostMapping("/me/car/add")
    public ResponseEntity<?> createCarByRequest(Authentication auth, @RequestBody CarRequest carReq){
        //認証ユーザーを取得する
        User authenticatedUser = us.getAuthenticatedUser(auth);
        //車を作成する
        FuelType fuelType = fts.getFuelTypeByName(carReq.getFuleTypeName());
        Car createdCar = cs.createCar(carReq.getDiscription(), fuelType, carReq.getKilometersPerLiter());
        //車への権限を付与する
        cas.createCarAuth(authenticatedUser, createdCar, true , true);
        //作成した車の情報を一部レスポンスする
        CreatedCarResponse createdCarRes = new CreatedCarResponse(createdCar.getAltId());
        return ResponseEntity.ok(createdCarRes);
    }

    @GetMapping("/me/car")
    public ResponseEntity<?> getCarsByAuthenticatedUser(Authentication auth){
        User user = us.getAuthenticatedUser(auth);
        List<CarAuth> carAuths = cas.getCarAuthsByUser(user);
        List<CarIndexResponse> carIndexReses = carAuths.stream()
                .map(carAuth -> new CarIndexResponse(carAuth.getCar().getAltId(), carAuth.getCar().getDiscription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carIndexReses);
    }

    @GetMapping("/me/car/{carAltId}")
    public ResponseEntity<?> getCarByCarAltId(Authentication auth , @PathVariable String carAltId){
        User user = us.getAuthenticatedUser(auth);
        Car car = cs.getCarByAlt(carAltId);
        cas.validateHasAuth(user , car);
        CarDetailsResponse carDetailsRes = new CarDetailsResponse(car.getDiscription(), car.getFuelType().getName(), car.getKilometersPerLiter());
        return ResponseEntity.ok(carDetailsRes);
    }

}
