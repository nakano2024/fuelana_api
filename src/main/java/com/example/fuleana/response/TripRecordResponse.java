package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripRecordResponse {

    private String tripRecordId;
    private String purposeName;
    private float totalYen;
    private float totalLiter;
    private float kilometers;
    private Timestamp createdAt;

}
