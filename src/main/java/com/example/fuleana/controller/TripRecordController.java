package com.example.fuleana.controller;

import com.example.fuleana.entity.*;
import com.example.fuleana.request.TripRecordRequest;
import com.example.fuleana.response.TripRecordResponse;
import com.example.fuleana.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.util.List;
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

    @PostMapping("/me/car/{carAltId}/trip-records/create")
    ResponseEntity<?> createTripRecords(Authentication auth, @PathVariable String carAltId, TripRecordRequest req){

        User authenticatedUser = us.getAuthenticatedUser(auth);
        Car requestedCar = cs.getCarByAlt(carAltId);
        cas.validateHasAuth(authenticatedUser, requestedCar);
        Purpose purpose = ps.getPurposeByName(req.getPurposeName());
        FuelPrice fuelPrice = fps.getLatestFuelPriceByFuelType(requestedCar.getFuelType());
        trs.createTripRecord(requestedCar, purpose, fuelPrice, req.getTotalKilometers());

        return ResponseEntity.ok("");
    }

    @GetMapping("/car/{carAltId}/trip-records")
    ResponseEntity<?> getTripRecordsByCarAltId(Authentication auth, @PathVariable String carAltId,
                                               @RequestParam(value = "purpose_name", required = true) @Pattern(regexp = "BUSINESS|PRIVATE") String purposeName,
                                               @RequestParam(value = "year", required = true) @Digits(integer = 4, fraction = 0) Integer year){

        User authenticatedUser = us.getAuthenticatedUser(auth);
        Car requestedCar = cs.getCarByAlt(carAltId);
        cas.validateHasAuth(authenticatedUser , requestedCar);
        Purpose purpose = ps.getPurposeByName(purposeName);
        List<TripRecord> tripRecords = trs.getTripRecordsByCarAndPurposeAndYear(requestedCar, purpose, year);
        List<TripRecordResponse> tripRecordReses = tripRecords.stream()
                .map(tr -> new TripRecordResponse(tr.getPurpose().getName(), tr.getTotalYen(), tr.getTotalKilometers(), tr.getCreatedAt()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tripRecordReses);
    }

}
