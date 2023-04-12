package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripRecordResponse {

    private String purposeName;
    private Float totalYen;
    private Float totalKilometers;
    private Timestamp createdAt;

}
