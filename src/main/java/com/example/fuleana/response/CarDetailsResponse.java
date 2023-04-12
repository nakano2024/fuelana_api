package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDetailsResponse {

    private String discription;
    private String fuelTypeName;
    private Float kilometersPerLiter;

}
