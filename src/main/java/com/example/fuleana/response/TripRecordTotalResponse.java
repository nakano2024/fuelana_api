package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripRecordTotalResponse {

    private float grandTotalYen;
    private float grandTotalLiter;
    private float grandTotalKilometers;

}
