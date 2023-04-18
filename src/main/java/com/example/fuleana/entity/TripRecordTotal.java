package com.example.fuleana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class TripRecordTotal {

    private Car car;
    private float grandTotalYen;
    private float grandTotalLiter;
    private float grandTotalKilometers;

    public TripRecordTotal(Car car, double grandTotalYen, double grandTotalLiter, double totalKilometers){
        this.car = car;
        this.grandTotalYen = (float) grandTotalYen;
        this.grandTotalLiter = (float) grandTotalLiter;
        this.grandTotalKilometers = (float) totalKilometers;
    }

}