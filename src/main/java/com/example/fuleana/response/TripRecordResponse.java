package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripRecordResponse {

    private String purpose_name;
    private Float total_yen;
    private Float total_kilometers;
    private Timestamp created_at;

}
