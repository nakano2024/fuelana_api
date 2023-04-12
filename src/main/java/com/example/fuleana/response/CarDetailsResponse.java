package com.example.fuleana.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDetailsResponse {

    private String discription;
    private String fuel_type_name;
    private Float kilometers_per_liter;

}
