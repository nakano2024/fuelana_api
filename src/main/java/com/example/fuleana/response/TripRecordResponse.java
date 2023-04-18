package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripRecordResponse {

    private String purpose_name;
    private float total_yen;
    private float total_liter;
    private float kilometers;
    private Timestamp created_at;

}
