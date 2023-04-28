package com.example.fuleana.entity;

import lombok.Data;


@Data
public class TripRecordsTotal {

    private Car car;
    private Purpose purpose;
    private float grandTotalYen;
    private float grandTotalLiter;
    private float grandTotalKilometers;

    public TripRecordsTotal(Car car , Purpose purpose , double grandTotalYen, double grandTotalLiter, double totalKilometers){
        this.car = car;
        this.purpose = purpose;
        this.grandTotalYen = (float) grandTotalYen;
        this.grandTotalLiter = (float) grandTotalLiter;
        this.grandTotalKilometers = (float) totalKilometers;
    }

}