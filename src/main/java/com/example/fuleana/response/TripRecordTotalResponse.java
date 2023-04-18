package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripRecordTotalResponse {

    private float grand_total_yen;
    private float grand_total_liter;
    private float grand_total_kilometers;

}
