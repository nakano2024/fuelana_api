package com.example.fuleana.controller;

import com.example.fuleana.entity.*;
import com.example.fuleana.request.TripRecordRequest;
import com.example.fuleana.response.TripRecordResponse;
import com.example.fuleana.response.TripRecordTotalResponse;
import com.example.fuleana.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TripRecordController {

    @Autowired
    TripRecordService trs;

    @Autowired
    UserService us;

    @Autowired
    CarService cs;

    @Autowired
    CarAuthService cas;

    @Autowired
    PurposeService ps;

    @Autowired
    FuelPriceService fps;

    @PostMapping("/me/cars/{carAltId}/trip-records/create")
    @Transactional
    ResponseEntity<?> createTripRecords(Authentication auth,@Valid @RequestBody TripRecordRequest req , @PathVariable String carAltId){

        User authenticatedUser = us.getAuthenticatedUser(auth);
        Car requestedCar = cs.getCarByAlt(carAltId);
        cas.validateHasAuth(authenticatedUser, requestedCar);
        Purpose purpose = ps.getPurposeByName(req.getPurposeName());
        FuelPrice fuelPrice = fps.getLatestFuelPriceByFuelType(requestedCar.getFuelType());
        trs.createTripRecord(requestedCar, purpose, fuelPrice, req.getTotalKilometers());
        return ResponseEntity.ok("");

    }

    @GetMapping("/me/cars/{carAltId}/trip-records")
    ResponseEntity<?> getTripRecordsByCarAltId(Authentication auth, @PathVariable String carAltId,
                                               @RequestParam(value = "year", required = true) @Digits(integer = 4, fraction = 0) int year,
                                               @RequestParam(value = "month", required = true) @Digits(integer = 2, fraction = 0) @Min(1) @Max(12) int month) {

        //権限のバリデーション
        User authenticatedUser = us.getAuthenticatedUser(auth);
        Car requestedCar = cs.getCarByAlt(carAltId);
        cas.validateHasAuth(authenticatedUser, requestedCar);
        //各走行データの一覧取得
        List<TripRecord> tripRecords = trs.getTripRecordsByCarAndYearAndMonth(requestedCar, year, month);
        //走行データの合計取得
        TripRecordTotal tripRecordTotal = trs.getTripRecordTotalByCarAndYearAndMonth(requestedCar, year, month);
        //レスポンスデータに変換
        List<TripRecordResponse> tripRecordReses = tripRecords.stream()
                .map(tr -> new TripRecordResponse(tr.getPurpose().getName(), tr.getTotalYen(), tr.getTotalLiter(), tr.getTotalKilometers(), tr.getCreatedAt()))
                .collect(Collectors.toList());
        TripRecordTotalResponse tripRecordTotalRes = new TripRecordTotalResponse(tripRecordTotal.getGrandTotalYen(), tripRecordTotal.getGrandTotalLiter(), tripRecordTotal.getGrandTotalKilometers());
        //jsonにまとめる
        Map<String, Object> res = new HashMap<>();
        res.put("total", tripRecordTotalRes);
        res.put("trip_records", tripRecordReses);
        return ResponseEntity.ok(res);

    }

}
